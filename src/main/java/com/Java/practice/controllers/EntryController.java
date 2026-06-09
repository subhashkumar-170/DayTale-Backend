package com.Java.practice.controllers;

import com.Java.practice.entity.Entry;
import com.Java.practice.services.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://day-tale-frontend-lcbv.vercel.app"
})
@RequestMapping("/entries")
public class EntryController {

    @Autowired
    private EntryService entryService;

    @PostMapping
    public Entry createEntry(@RequestBody Entry entry){

        Authentication auth =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String username = auth.getName();

        entry.setUsername(username);

        entry.setDate(LocalDateTime.now());

        return entryService.saveEntry(entry);
    }
    @GetMapping("/hello")
    public String hello() {
        return "ENTRY CONTROLLER REAL";
    }

    @GetMapping
    public List<Entry> getAllEntries(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return entryService.getEntriesByUsername(auth.getName());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id){

        Authentication auth =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String username = auth.getName();

        boolean isDeleted =
                entryService.deleteEntryByOwner(id, username);

        if(isDeleted){
            return ResponseEntity.ok("Entry deleted successfully");
        }

        return ResponseEntity.status(403).body("Not allowed to delete this entry");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entry> updateEntry(
            @PathVariable String id,
            @RequestBody Entry entry) {

        Authentication auth =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String username = auth.getName();

        Entry updatedEntry =
                entryService.updateEntryByOwner(id, entry, username);

        if (updatedEntry != null) {
            return ResponseEntity.ok(updatedEntry);
        }

        return ResponseEntity.status(403).build();
    }

}
