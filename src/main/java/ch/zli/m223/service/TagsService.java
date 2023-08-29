package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.Tags;


@ApplicationScoped
public class TagsService {

    @Inject
    private EntityManager tagManager;

    @Transactional
    public Tags createTag(Tags tag) {
        tagManager.persist(tag);
        return tag;
    }

    public List<Tags> findAll() {
        var query = tagManager.createQuery("FROM Tags", Tags.class);
        return query.getResultList();
    }

    @Transactional
    public void deleteTag(long id) {
        Tags tag = tagManager.find(Tags.class, id);
        tagManager.remove(tag);
    }

    @Transactional
    public Tags updateTag(Tags tag) {
        tagManager.merge(tag);
        return tag;
    }

}
