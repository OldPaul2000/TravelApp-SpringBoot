package com.travelapp.travelapp.repository;

import jakarta.persistence.Persistence;

// This class is used as value for valueFilter parameter in @JsonInclude annotation for preventing
// fetching lazy type annotated entity fields.
// If this annotation is not present on entity's field/fields, those fields will be fetched
// when using rest apis despite they are annotated with fetch = FetchType.LAZY

public class LazyFieldsFilter {

    @Override
    public boolean equals(Object obj) {
        return !Persistence.getPersistenceUtil().isLoaded(obj);
    }
}
