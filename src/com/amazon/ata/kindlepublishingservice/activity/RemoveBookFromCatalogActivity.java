package com.amazon.ata.kindlepublishingservice.activity;
import com.amazon.ata.kindlepublishingservice.dao.CatalogDao;
import com.amazon.ata.kindlepublishingservice.dynamodb.models.CatalogItemVersion;
import com.amazon.ata.kindlepublishingservice.models.requests.RemoveBookFromCatalogRequest;
import com.amazon.ata.kindlepublishingservice.models.response.RemoveBookFromCatalogResponse;

import javax.inject.Inject;

public class RemoveBookFromCatalogActivity {
    CatalogDao catalogDao;

    @Inject
            public RemoveBookFromCatalogActivity(CatalogDao catalogDao) {
        this.catalogDao = catalogDao;
    }

    public RemoveBookFromCatalogResponse execute(RemoveBookFromCatalogRequest removeBookFromCatalogRequest) {
        CatalogItemVersion book = catalogDao.deleteBookFromCatalog(removeBookFromCatalogRequest.getBookId());
        return RemoveBookFromCatalogResponse.builder()
                .withId(book.getBookId())
                .isInactive(book.isInactive())
                .build();
    }
}

