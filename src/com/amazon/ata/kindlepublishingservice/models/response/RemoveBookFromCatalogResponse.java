package com.amazon.ata.kindlepublishingservice.models.response;
import java.util.Objects;
public class RemoveBookFromCatalogResponse {
    private String id;
    private boolean inactive;
    public RemoveBookFromCatalogResponse() {

    }
    public RemoveBookFromCatalogResponse(Builder builder) {
        this.id = builder.id;
        this.inactive = builder.inactive;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isInactive() {
        return inactive;
    }

    public void setInactive(boolean inactive) {
        this.inactive = inactive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RemoveBookFromCatalogResponse that = (RemoveBookFromCatalogResponse) o;
        return isInactive() == that.isInactive() && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), isInactive());
    }

    @Override
    public String toString() {
        return "RemoveBookFromCatalogResponse{" +
                "id='" + id + '\'' +
                ", inactive=" + inactive +
                '}';
    }

    public static Builder builder() {return new Builder();}

    public static final class Builder {
        private String id;
        private boolean inactive;

        private Builder() {}

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder isInactive(boolean inactive) {
            this.inactive = inactive;
            return this;
        }

        public RemoveBookFromCatalogResponse build() {return new RemoveBookFromCatalogResponse(this);
        }
    }

}
