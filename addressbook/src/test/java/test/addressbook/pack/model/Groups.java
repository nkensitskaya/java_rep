package test.addressbook.pack.model;

import com.google.common.collect.ForwardingSet;

import java.util.*;

public class Groups extends ForwardingSet<GroupData> {

    private Set<GroupData> delegate;

    public Groups(Groups groups) {
        this.delegate = new HashSet<GroupData>(groups.delegate());
    }

    public Groups() {
        this.delegate = new HashSet<GroupData>();
    }

    public Groups(Collection<GroupData> groups) {
        this.delegate = new HashSet<GroupData>(groups);
    }

    @Override
    protected Set<GroupData> delegate() {
        return delegate;
    }


    public Groups withAdded(GroupData group) {
        Groups groups = new Groups(this);
        groups.add(group);
        return groups;
    }

    public Groups without(GroupData group) {
        Groups groups = new Groups(this);
        groups.remove(group);
        return groups;
    }

    public Boolean isIdExists(Integer groupId) {
        Groups groups = new Groups(this);
        boolean isIdExists = false;

        Iterator<GroupData> iterator = groups.delegate.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId() == groupId) {
                isIdExists = true;
                break;
            }
        }
        return isIdExists;
    }
}
