package com.tuition.app.repository.specification;

import com.tuition.app.entity.TutorProfile;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class TutorSpecification {

    public static Specification<TutorProfile> hasSubject(String subject) {
        return (root, query, cb) -> 
            subject == null ? null : cb.like(cb.lower(root.get("subjects")), "%" + subject.toLowerCase() + "%");
    }

    public static Specification<TutorProfile> hasClassLevel(String classLevel) {
        return (root, query, cb) -> 
            classLevel == null ? null : cb.like(cb.lower(root.get("classLevel")), "%" + classLevel.toLowerCase() + "%");
    }

    public static Specification<TutorProfile> hasCity(String city) {
        return (root, query, cb) -> 
            city == null ? null : cb.equal(cb.lower(root.get("city")), city.toLowerCase());
    }

    public static Specification<TutorProfile> priceGreaterThan(Double minPrice) {
        return (root, query, cb) -> 
            minPrice == null ? null : cb.greaterThanOrEqualTo(root.get("hourlyRate"), minPrice);
    }

    public static Specification<TutorProfile> priceLessThan(Double maxPrice) {
        return (root, query, cb) -> 
            maxPrice == null ? null : cb.lessThanOrEqualTo(root.get("hourlyRate"), maxPrice);
    }
}
