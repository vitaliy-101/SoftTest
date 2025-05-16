package org.example.softproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.softproject.dto.FileRequestDto;
import org.example.softproject.service.NumberSelectionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Controller for handling requests to find the Nth minimum number from an Excel file.
 */
@RestController
@RequestMapping("/api/numbers")
@Tag(name = "Number Controller", description = "Controller for finding Nth minimum number in Excel files")
public class NumberController {

    private final NumberSelectionService numberSelectionService;

    public NumberController(NumberSelectionService numberSelectionService) {
        this.numberSelectionService = numberSelectionService;
    }

    /**
     * Finds the Nth minimum number from the specified Excel file.
     *
     * @param request DTO containing file path and N value
     * @return the Nth minimum number
     */
    @PostMapping("/find")
    @Operation(description = "Returns the Nth minimum number from the specified Excel file")
    public int findNthMinimumNumber(@RequestBody FileRequestDto request) throws IOException {
        if (!Files.exists(Paths.get(request.getFilePath()))) {
            throw new IllegalArgumentException("File not found: " + request.getFilePath());
        }
        return numberSelectionService.findNthMinimumNumber(request.getFilePath(), request.getN());
    }
}