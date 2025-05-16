package org.example.softproject.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO for receiving file path and N value in the request.
 */
@Schema(description = "Request body for finding Nth minimum number")
public class FileRequestDto {

    @Schema(description = "Path to the Excel file containing numbers", example = "C:/data/numbers.xlsx")
    private String filePath;

    @Schema(description = "The position of the minimum number to find (1 for smallest, 2 for second smallest, etc.)", example = "3")
    private int n;

    // Getters and setters
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }
}