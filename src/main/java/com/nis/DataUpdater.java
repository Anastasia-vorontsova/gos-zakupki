package com.nis;

import java.io.File;
import java.io.IOException;
import javax.annotation.PostConstruct;
import org.springframework.scheduling.annotation.Scheduled;

public abstract class DataUpdater {

    private final DataProcessingService dataProcessingService;
    private String checksum = "";

    public DataUpdater(DataProcessingService dataProcessingService) {
        this.dataProcessingService= dataProcessingService;
    }

    @Scheduled(fixedDelay = 15000)
    public void some() throws IOException {
        File file = dataProcessingService.downloadFile();
        String newChecksum = dataProcessingService.checksum(file);

        if (!checksum.equals(newChecksum)) {
            System.out.println("Remote Data updated. Will invoke updateData()");
            checksum = newChecksum;

            updateData(file);
        }
    }

    public abstract void updateData(File file);

    @PostConstruct
    public void init() throws IOException {
        File file = dataProcessingService.downloadFile();
        checksum = dataProcessingService.checksum(file);

        updateData(file);
    }

}
