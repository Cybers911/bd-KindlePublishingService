package com.amazon.ata.kindlepublishingservice.publishing;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import dagger.Provides;

@Singleton
public class BookPublishRequestManager {
    private final Queue<BookPublishRequest> requestQueue;

    @Inject
    public BookPublishRequestManager() {
        requestQueue = new ConcurrentLinkedQueue<>();
    }

    public void addBookPublishRequest(BookPublishRequest bookPublishRequest) {
        // Add bookPublishRequest to the queue or database
        requestQueue.add(bookPublishRequest);
    }

public BookPublishRequest getBookPublishRequestToProcess() {
        // Retrieve and remove a bookPublishRequest from the queue or database
        if (requestQueue.isEmpty()) {
            return null;
        }

    return requestQueue.poll();

}
}