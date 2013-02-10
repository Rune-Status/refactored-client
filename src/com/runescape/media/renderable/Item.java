package com.runescape.media.renderable;

import com.runescape.cache.def.ItemDefinition;
import com.runescape.media.Model;

public class Item extends Renderable {

	public int itemId;
	public int itemCount;

	@Override
	public final Model getRotatedModel() {
		ItemDefinition itemdefinition = ItemDefinition.getDefinition(itemId);
		return itemdefinition.getAmountModel(itemCount);
	}
}