package io.pivotal.pal.tracker;

import java.util.*;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private Map<Long, TimeEntry> timeEntryMap = new HashMap<Long, TimeEntry>();


    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        Long id  = Long.valueOf(this.timeEntryMap.values().size()) + 1L;
        timeEntry.setId(id);
        this.timeEntryMap.put(id, timeEntry);
        return timeEntry;
    }

    @Override
    public TimeEntry find(Long timeEntryId) {
        return this.timeEntryMap.get(timeEntryId);
    }

    @Override
    public List<TimeEntry> list() {

       return new ArrayList<TimeEntry>(this.timeEntryMap.values());

    }

    @Override
    public TimeEntry update(Long eq, TimeEntry any) {
        any.setId(eq);
        this.timeEntryMap.put(eq, any);
        return any;
    }

    @Override
    public void delete(Long timeEntryId) {
        this.timeEntryMap.remove(timeEntryId);
    }

}


