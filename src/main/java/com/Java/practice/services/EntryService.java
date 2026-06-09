package com.Java.practice.services;

import com.Java.practice.entity.Entry;
import com.Java.practice.repository.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EntryService {

    @Autowired
    private EntryRepository entryRepository;

    public Entry saveEntry(Entry entry){
        return entryRepository.save(entry);
    }

    public List<Entry> getAll(){
        return entryRepository.findAll();
    }

    public Entry getEntryById(String id) {
        return entryRepository.findById(id).orElse(null);
    }

    public boolean deleteEntryByOwner(String id, String username) {
        Entry entry = entryRepository.findById(id).orElse(null);

        if (entry == null) {
            return false;
        }

        if (!entry.getUsername().equals(username)) {
            return false;
        }

        entryRepository.deleteById(id);
        return true;
    }

    public Entry updateEntryByOwner(String id, Entry newEntry, String username) {
        Entry oldEntry = entryRepository.findById(id).orElse(null);

        if (oldEntry == null) {
            return null;
        }

        if (!oldEntry.getUsername().equals(username)) {
            return null;
        }

        oldEntry.setTitle(newEntry.getTitle());
        oldEntry.setContent(newEntry.getContent());
        oldEntry.setMood(newEntry.getMood());

        return entryRepository.save(oldEntry);
    }

    public List<Entry> getEntriesByUsername(String username){
        return entryRepository.findByUsername(username);
    }

    public boolean deleteEntry(String id){
        if(entryRepository.existsById(id)){
            entryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Entry updateEntry(String id, Entry newEntry){
        Entry oldEntry = entryRepository.findById(id).orElse(null);

        if (oldEntry == null) {
            return null;
        }

        oldEntry.setTitle(newEntry.getTitle());
        oldEntry.setContent(newEntry.getContent());
        oldEntry.setMood(newEntry.getMood());

        return entryRepository.save(oldEntry);
    }



}
