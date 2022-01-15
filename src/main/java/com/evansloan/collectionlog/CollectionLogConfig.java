package com.evansloan.collectionlog;

import java.awt.Color;
import net.runelite.client.config.Alpha;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("collectionlog")
public interface CollectionLogConfig extends Config
{
	@ConfigItem(
		keyName = "display_unique_items",
		name = "Display unique items",
		description = "Display unique obtained collection log items",
		position = 1
	)
	default boolean displayUniqueItems()
	{
		return true;
	}

	@ConfigItem(
		keyName = "display_total_items",
		name = "Display total items",
		description = "Display total obtained collection log items",
		position = 2
	)
	default boolean displayTotalItems()
	{
		return true;
	}

	@ConfigItem(
		keyName = "display_as_percentage",
		name = "Display as percentage",
		description = "Display collection log progress as a percentage",
		position = 3
	)
	default boolean displayAsPercentage()
	{
		return false;
	}

	@Alpha
	@ConfigItem(
		keyName = "highlight_color",
		name = "Completed entry highlight color",
		description = "Sets the highlight color of completed entries",
		position = 4
	)
	default Color highlightColor()
	{
		return new Color(13, 193, 13);
	}

	@ConfigItem(
		keyName = "notify_on_export",
		name = "Notify on export",
		description = "Send a notification on collection log export",
		position = 5
	)
	default boolean notifyOnExport()
	{
		return true;
	}

	@ConfigItem(
		keyName = "export_chat_message",
		name = "Chat message on export",
		description = "Show exported file location in chat box on collection log export",
		position = 6
	)
	default boolean sendExportChatMessage()
	{
		return true;
	}

	@ConfigItem(
		keyName = "upload_collection_log",
		name = "Upload collection log data to collectionlog.net",
		description = "Upload saved collection log data to collectionlog.net on log out",
		position = 7
	)
	default boolean uploadCollectionLog()
	{
		return false;
	}
}
