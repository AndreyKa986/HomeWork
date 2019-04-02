package finalProject.threads;

import finalProject.entities.Store;

import java.util.List;

interface StoreListener {
    void onComplete(List<Store> list);
}

