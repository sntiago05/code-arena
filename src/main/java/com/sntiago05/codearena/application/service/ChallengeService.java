package com.sntiago05.codearena.application.service;

import com.sntiago05.codearena.application.ports.in.challenge.CreateChallengeUseCase;
import com.sntiago05.codearena.application.ports.in.challenge.GetChallengeByIdUseCase;
import com.sntiago05.codearena.application.ports.in.command.CreateChallengeCommand;
import com.sntiago05.codearena.application.ports.in.command.challenges.GetChallengeByIdCommand;
import com.sntiago05.codearena.application.ports.in.result.ChallengeCreated;
import com.sntiago05.codearena.application.ports.in.result.GetChallengeResult;
import com.sntiago05.codearena.application.ports.out.CategoryRepositoryPort;
import com.sntiago05.codearena.application.ports.out.ChallengeRepositoryPort;
import com.sntiago05.codearena.application.service.mapper.challengeservicemapper.CreateChallengeMapper;
import com.sntiago05.codearena.application.service.mapper.challengeservicemapper.GetChallengeMapper;
import com.sntiago05.codearena.domain.challenge.Challenge;
import com.sntiago05.codearena.domain.challenge.ChallengeCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChallengeService implements CreateChallengeUseCase, GetChallengeByIdUseCase {

    private final ChallengeRepositoryPort challengeRepositoryPort;
    private final CategoryRepositoryPort categoryRepositoryPort;

    @Override
    public ChallengeCreated create(CreateChallengeCommand command) {
        if (challengeRepositoryPort.existsByTitle(command.title())) throw new RuntimeException();
        ChallengeCategory category = categoryRepositoryPort.findBydId(command.categoryId()).orElseThrow();

        Challenge challenge = CreateChallengeMapper.toChallenge(command, category);
        Challenge saved = challengeRepositoryPort.save(challenge);
        return CreateChallengeMapper.toResult(saved);
    }

    @Override
    public GetChallengeResult findById(GetChallengeByIdCommand command) {
        return GetChallengeMapper.toResult(challengeRepositoryPort.findById(command.uuid()).orElseThrow());
    }
}
