package io.pivotal.pal.tracker;

import java.util.*;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    Map<Long, TimeEntry> timeEntryRepo = new HashMap<Long, TimeEntry>();

    private long currentId = 1L;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        Long id = currentId++;

        TimeEntry newTimeEntry = new TimeEntry(
                id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()
        );

        timeEntryRepo.put(id, newTimeEntry);
        return newTimeEntry;
    }

    @Override
    public TimeEntry find(long id) {
        return timeEntryRepo.get(id);
    }

    @Override
    public List<TimeEntry> list() {
        List<TimeEntry> timeEntries = new ArrayList<TimeEntry>();
        for(Long entry: timeEntryRepo.keySet()) {
            timeEntries.add(timeEntryRepo.get(entry));
        }
        return timeEntries;
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        if(!timeEntryRepo.containsKey(id))
        {
            return null;
        }
        TimeEntry newTimeEntry = new TimeEntry(
                id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()
        );

        timeEntryRepo.put(id,newTimeEntry);
        return newTimeEntry;
    }

    @Override
    public void delete(long id) {
        if(timeEntryRepo.containsKey(id)){
            timeEntryRepo.remove(id);
        }
    }
}
