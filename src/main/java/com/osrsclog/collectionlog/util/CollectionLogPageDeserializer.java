package com.osrsclog.collectionlog.util;

import com.osrsclog.collectionlog.CollectionLogItem;
import com.osrsclog.collectionlog.CollectionLogKillCount;
import com.osrsclog.collectionlog.CollectionLogPage;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollectionLogPageDeserializer implements JsonDeserializer<CollectionLogPage>
{
    private static final String COLLECTION_LOG_ITEMS_KEY = "items";
    private static final String COLLECTION_LOG_KILL_COUNTS_KEY = "killCounts";
    private static final String COLLECTION_LOG_TOTAL_OBTAINED_KEY = "totalObtained";
    private static final String COLLECTION_LOG_TOTAL_ITEMS_KEY = "totalItems";
    private static final String COLLECTION_LOG_UNIQUE_OBTAINED_KEY = "uniqueObtained";
    private static final String COLLECTION_LOG_UNIQUE_ITEMS_KEY = "uniqueItems";
    private static final String COLLECTION_LOG_IS_UPDATED_KEY = "isUpdated";
    private static String page = "";

    private final Map<String, String> keyMap = new HashMap<String, String>()
    {
        {
            put(COLLECTION_LOG_KILL_COUNTS_KEY, COLLECTION_LOG_KILL_COUNTS_KEY);
            put(COLLECTION_LOG_TOTAL_OBTAINED_KEY, COLLECTION_LOG_TOTAL_OBTAINED_KEY);
            put(COLLECTION_LOG_TOTAL_ITEMS_KEY, COLLECTION_LOG_TOTAL_ITEMS_KEY);
            put(COLLECTION_LOG_UNIQUE_OBTAINED_KEY, COLLECTION_LOG_UNIQUE_OBTAINED_KEY);
            put(COLLECTION_LOG_UNIQUE_ITEMS_KEY, COLLECTION_LOG_UNIQUE_ITEMS_KEY);
        }
    };

    public CollectionLogPageDeserializer(String pageName)
    {
        super();

        page = pageName;
    }

    @Override
    public CollectionLogPage deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException
    {
        JsonObject jsonObjectPage = jsonElement.getAsJsonObject();

        List<CollectionLogItem> newItems = new ArrayList<>();

        for (JsonElement item : jsonObjectPage.get(COLLECTION_LOG_ITEMS_KEY).getAsJsonArray())
        {
            CollectionLogItem newItem = context.deserialize(item, CollectionLogItem.class);
            newItems.add(newItem);
        }

        List<CollectionLogKillCount> newKillCounts = new ArrayList<>();
        JsonElement pageKillCounts = jsonObjectPage.get(keyMap.get(COLLECTION_LOG_KILL_COUNTS_KEY));

        if (pageKillCounts != null)
        {
            for (JsonElement killCount : pageKillCounts.getAsJsonArray())
            {
                CollectionLogKillCount newKillCount;
                newKillCount = context.deserialize(killCount, CollectionLogKillCount.class);
                newKillCounts.add(newKillCount);
            }
        }

        boolean isUpdated = jsonObjectPage.get(COLLECTION_LOG_IS_UPDATED_KEY) != null
                && jsonObjectPage.get(COLLECTION_LOG_IS_UPDATED_KEY).getAsBoolean();

        return new CollectionLogPage(
                page,
                newItems,
                newKillCounts,
                isUpdated
        );
    }
}
