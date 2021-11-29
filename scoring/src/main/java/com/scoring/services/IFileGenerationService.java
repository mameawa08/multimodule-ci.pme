package com.scoring.services;

import com.scoring.dto.RapportFile;
import com.scoring.exceptions.FileGenerationException;
import com.scoring.payloads.RapportPayload;

public interface IFileGenerationService {
    RapportFile generateRapport(Long id, RapportPayload payload) throws FileGenerationException;
}
