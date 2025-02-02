package com.amazon.ata.kindlepublishingservice.publishing;

import com.amazon.ata.kindlepublishingservice.dao.CatalogDao;
import com.amazon.ata.kindlepublishingservice.dao.PublishingStatusDao;
import com.amazon.ata.kindlepublishingservice.dynamodb.models.CatalogItemVersion;
import com.amazon.ata.kindlepublishingservice.dynamodb.models.PublishingStatusItem;
import com.amazon.ata.kindlepublishingservice.enums.PublishingRecordStatus;
import com.amazon.ata.kindlepublishingservice.exceptions.BookNotFoundException;

import javax.inject.Inject;
import javax.xml.catalog.Catalog;

public class BookPublishTask implements Runnable {
    private final PublishingStatusDao publishingStatusDao;
    private final CatalogDao catalogDao;
    private final BookPublishRequestManager bookPublishRequestManager;


    @Inject
    public BookPublishTask(PublishingStatusDao publishingStatusDao, CatalogDao catalogDao,
                           BookPublishRequestManager bookPublishRequestManager) {
        this.publishingStatusDao = publishingStatusDao;
        this.catalogDao = catalogDao;
        this.bookPublishRequestManager = bookPublishRequestManager;
    }

    @Override
    public void run() {
        BookPublishRequest request = bookPublishRequestManager.getBookPublishRequestToProcess();
        if (request == null) {
            // No book requests to process
            return;
        }


        // 1. Adds an entry to the Publishing Status table with state IN_PROGRESS
        publishingStatusDao.setPublishingStatus(request.getPublishingRecordId(),
                PublishingRecordStatus.IN_PROGRESS, request.getBookId());

        //2. Performs formatting and conversion of the book
        KindleFormattedBook book = KindleFormatConverter.format(request);

        //3. Adds the new book to the CatalogItemVersion table
        try {
            CatalogItemVersion itemVersion = catalogDao.createOrUpdateBook(book);
            publishingStatusDao.setPublishingStatus(request.getPublishingRecordId(), PublishingRecordStatus.SUCCESSFUL,
                    itemVersion.getBookId());
        } catch (BookNotFoundException e) {
            publishingStatusDao.setPublishingStatus(request.getPublishingRecordId(), PublishingRecordStatus.FAILED,
                    request.getBookId(), e.getMessage());
        }

    }

}

