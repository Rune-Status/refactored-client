package com.jagex.runescape.cache.media;

import com.jagex.runescape.Game;
import com.jagex.runescape.cache.Archive;
import com.jagex.runescape.cache.def.ActorDefinition;
import com.jagex.runescape.cache.def.ItemDefinition;
import com.jagex.runescape.collection.Cache;
import com.jagex.runescape.media.Animation;
import com.jagex.runescape.media.renderable.Model;
import com.jagex.runescape.net.Buffer;
import com.jagex.runescape.util.TextUtils;

public class Widget {

	public String optionText;
	public ImageRGB disabledImage;
	public int imageY[];
	public static Archive mediaArchive;
	public int id;
	public static Widget interfaces[];
	public boolean itemDeletesDraged;
	public int anInt218;
	public boolean hiddenUntilHovered;
	public byte alpha;
	public int imageX[];
	public int optionAttributes;
	public static TypeFace fonts[];
	public int itemAmounts[];
	public int unknownOne;
	public int enabledHoveredColor;
	public int anInt227;
	public int anInt228;
	public boolean isInventory;
	public String disabledText;
	public int anInt231;
	public int childrenX[];
	public boolean unknownTwo;
	public int opcodes[][];
	public int anInt235;
	public int type;
	public TypeFace typeFaces;
	public int height;
	public boolean filled;
	public int disabledColor;
	public int width;
	public int contentType;
	public static int anInt243;
	public int itemSpritePadsY;
	public ImageRGB enabledImage;
	public static int anInt246 = -1;
	public boolean typeFaceShadowed;
	public int parentId;
	public String enabledText;
	public static Cache spriteCache;
	public int zoom;
	public int rotationX;
	public int rotationY;
	public int hoveredPopup;
	public static int anInt255 = -1;
	public int conditionValues[];
	public int children[];
	public int anInt259;
	public int enabledColor;
	public int disabledHoveredColor;
	public String options[];
	public int itemSpritePadsX;
	public static Cache modelCache = new Cache(30);
	public ImageRGB images[];
	public int enabledModelType;
	public int enabledModelId;
	public String tooltip;
	public int items[];
	public boolean typeFaceCentered;
	public int conditionTypes[];
	public boolean itemSwapable;
	public int childrenY[];
	public static int anInt277 = -1;
	public static int anInt280;
	public String optionCircumfix;
	public static byte data[][];
	public int modelType;
	public int modelId;
	public int scrollLimit;
	public int disabledAnimation;
	public int enabledAnimation;
	public boolean itemUsable;
	public int actionType;

	public static ImageRGB getImage(int spriteId, String spriteName) {
		long spriteHash = (TextUtils.spriteToHash(spriteName) << 8) + spriteId;
		ImageRGB sprite = (ImageRGB) spriteCache.get(spriteHash);
		if (sprite != null)
			return sprite;
		if (mediaArchive == null)
			return null;
		try {
			sprite = new ImageRGB(mediaArchive, spriteName, spriteId);
			spriteCache.put(sprite, spriteHash);
		} catch (Exception _ex) {
			return null;
		}
		return sprite;
	}

	public static Widget forId(int id) {
		if (interfaces[id] == null) {
			Buffer buf = new Buffer(data[id]);
			int j = buf.getUnsignedLEShort();
			interfaces[id] = parse(j, buf, id);
		}
		return interfaces[id];
	}

	public void swapItems(int originalSlot, int newSlot) {
		int originalItem = items[originalSlot];
		items[originalSlot] = items[newSlot];
		items[newSlot] = originalItem;
		originalItem = itemAmounts[originalSlot];
		itemAmounts[originalSlot] = itemAmounts[newSlot];
		itemAmounts[newSlot] = originalItem;
	}

	public Model getModel(int modelType, int modelId) {
		ItemDefinition item = null;
		if (modelType == 4) {
			item = ItemDefinition.lookup(modelId);
			anInt280 += item.ambience;
			anInt243 += item.diffusion;
		}
		Model model = (Model) modelCache.get((modelType << 16) + modelId);
		if (model != null)
			return model;
		if (modelType == 1)
			model = Model.getModel(modelId);
		if (modelType == 2)
			model = ActorDefinition.getDefinition(modelId).getHeadModel();
		if (modelType == 3)
			model = Game.localPlayer.getHeadModel();
		if (modelType == 4)
			model = item.asStack(50);
		if (modelType == 5)
			model = null;
		if (model != null)
			modelCache.put(model, (modelType << 16) + modelId);
		return model;
	}

	public static Widget parse(int parentId, Buffer buffer, int widgetIndex) {
		Widget widget = new Widget();
		widget.id = widgetIndex;
		widget.parentId = parentId;
		widget.type = buffer.getUnsignedByte();
		widget.actionType = buffer.getUnsignedByte();
		widget.contentType = buffer.getUnsignedLEShort();
		widget.width = buffer.getUnsignedLEShort();
		widget.height = buffer.getUnsignedLEShort();
		widget.alpha = (byte) buffer.getUnsignedByte();
		widget.hoveredPopup = buffer.getUnsignedByte();
		if (widget.hoveredPopup != 0)
			widget.hoveredPopup = (widget.hoveredPopup - 1 << 8) + buffer.getUnsignedByte();
		else
			widget.hoveredPopup = -1;
		if (widget.contentType == 600)
			anInt246 = parentId;
		if (widget.contentType == 650)
			anInt255 = parentId;
		if (widget.contentType == 655)
			anInt277 = parentId;
		int conditionCount = buffer.getUnsignedByte();
		if (conditionCount > 0) {
			widget.conditionTypes = new int[conditionCount];
			widget.conditionValues = new int[conditionCount];
			for (int condition = 0; condition < conditionCount; condition++) {
				widget.conditionTypes[condition] = buffer.getUnsignedByte();
				widget.conditionValues[condition] = buffer.getUnsignedLEShort();
			}

		}
		int opcodeCount = buffer.getUnsignedByte();
		if (opcodeCount > 0) {
			widget.opcodes = new int[opcodeCount][];
			for (int opcode = 0; opcode < opcodeCount; opcode++) {
				int subOpcodeCount = buffer.getUnsignedLEShort();
				widget.opcodes[opcode] = new int[subOpcodeCount];
				for (int subOpcode = 0; subOpcode < subOpcodeCount; subOpcode++)
					widget.opcodes[opcode][subOpcode] = buffer.getUnsignedLEShort();

			}

		}
		if (widget.type == 0) {
			widget.scrollLimit = buffer.getUnsignedLEShort();
			widget.hiddenUntilHovered = buffer.getUnsignedByte() == 1;
			int childrenCount = buffer.getUnsignedLEShort();
			widget.children = new int[childrenCount];
			widget.childrenX = new int[childrenCount];
			widget.childrenY = new int[childrenCount];
			for (int child = 0; child < childrenCount; child++) {
				widget.children[child] = buffer.getUnsignedLEShort();
				widget.childrenX[child] = buffer.getSignedShort();
				widget.childrenY[child] = buffer.getSignedShort();
			}

		}
		if (widget.type == 1) {
			widget.unknownOne = buffer.getUnsignedLEShort();
			widget.unknownTwo = buffer.getUnsignedByte() == 1;
		}
		if (widget.type == 2) {
			widget.items = new int[widget.width * widget.height];
			widget.itemAmounts = new int[widget.width * widget.height];
			widget.itemSwapable = buffer.getUnsignedByte() == 1;
			widget.isInventory = buffer.getUnsignedByte() == 1;
			widget.itemUsable = buffer.getUnsignedByte() == 1;
			widget.itemDeletesDraged = buffer.getUnsignedByte() == 1;
			widget.itemSpritePadsX = buffer.getUnsignedByte();
			widget.itemSpritePadsY = buffer.getUnsignedByte();
			widget.imageX = new int[20];
			widget.imageY = new int[20];
			widget.images = new ImageRGB[20];
			for (int sprite = 0; sprite < 20; sprite++) {
				int hasSprite = buffer.getUnsignedByte();
				if (hasSprite == 1) {
					widget.imageX[sprite] = buffer.getSignedShort();
					widget.imageY[sprite] = buffer.getSignedShort();
					String spriteName = buffer.getString();
					if (spriteName.length() > 0) {
						int spriteId = spriteName.lastIndexOf(",");
						widget.images[sprite] = getImage(Integer.parseInt(spriteName.substring(spriteId + 1)),
								spriteName.substring(0, spriteId));
					}
				}
			}

			widget.options = new String[5];
			for (int optionId = 0; optionId < 5; optionId++) {
				widget.options[optionId] = buffer.getString();
				if (widget.options[optionId].length() == 0)
					widget.options[optionId] = null;
			}

		}
		if (widget.type == 3)
			widget.filled = buffer.getUnsignedByte() == 1;
		if (widget.type == 4 || widget.type == 1) {
			widget.typeFaceCentered = buffer.getUnsignedByte() == 1;
			int typeFace = buffer.getUnsignedByte();
			if (fonts != null)
				widget.typeFaces = fonts[typeFace];
			widget.typeFaceShadowed = buffer.getUnsignedByte() == 1;
		}
		if (widget.type == 4) {
			widget.disabledText = buffer.getString();
			widget.enabledText = buffer.getString();
		}
		if (widget.type == 1 || widget.type == 3 || widget.type == 4)
			widget.disabledColor = buffer.getInt();
		if (widget.type == 3 || widget.type == 4) {
			widget.enabledColor = buffer.getInt();
			widget.disabledHoveredColor = buffer.getInt();
			widget.enabledHoveredColor = buffer.getInt();
		}
		if (widget.type == 5) {
			String spriteName = buffer.getString();
			if (spriteName.length() > 0) {
				int spriteId = spriteName.lastIndexOf(",");
				widget.disabledImage = getImage(Integer.parseInt(spriteName.substring(spriteId + 1)), spriteName.substring(0,
						spriteId));
			}
			spriteName = buffer.getString();
			if (spriteName.length() > 0) {
				int spriteId = spriteName.lastIndexOf(",");
				widget.enabledImage = getImage(Integer.parseInt(spriteName.substring(spriteId + 1)), spriteName.substring(0,
						spriteId));
			}
		}
		if (widget.type == 6) {
			widgetIndex = buffer.getUnsignedByte();
			if (widgetIndex != 0) {
				widget.modelType = 1;
				widget.modelId = (widgetIndex - 1 << 8) + buffer.getUnsignedByte();
			}
			widgetIndex = buffer.getUnsignedByte();
			if (widgetIndex != 0) {
				widget.enabledModelType = 1;
				widget.enabledModelId = (widgetIndex - 1 << 8) + buffer.getUnsignedByte();
			}
			widgetIndex = buffer.getUnsignedByte();
			if (widgetIndex != 0)
				widget.disabledAnimation = (widgetIndex - 1 << 8) + buffer.getUnsignedByte();
			else
				widget.disabledAnimation = -1;
			widgetIndex = buffer.getUnsignedByte();
			if (widgetIndex != 0)
				widget.enabledAnimation = (widgetIndex - 1 << 8) + buffer.getUnsignedByte();
			else
				widget.enabledAnimation = -1;
			widget.zoom = buffer.getUnsignedLEShort();
			widget.rotationX = buffer.getUnsignedLEShort();
			widget.rotationY = buffer.getUnsignedLEShort();
		}
		if (widget.type == 7) {
			widget.items = new int[widget.width * widget.height];
			widget.itemAmounts = new int[widget.width * widget.height];
			widget.typeFaceCentered = buffer.getUnsignedByte() == 1;
			int typeFaceCount = buffer.getUnsignedByte();
			if (fonts != null)
				widget.typeFaces = fonts[typeFaceCount];
			widget.typeFaceShadowed = buffer.getUnsignedByte() == 1;
			widget.disabledColor = buffer.getInt();
			widget.itemSpritePadsX = buffer.getSignedShort();
			widget.itemSpritePadsY = buffer.getSignedShort();
			widget.isInventory = buffer.getUnsignedByte() == 1;
			widget.options = new String[5];
			for (int optionId = 0; optionId < 5; optionId++) {
				widget.options[optionId] = buffer.getString();
				if (widget.options[optionId].length() == 0)
					widget.options[optionId] = null;
			}

		}
		if (widget.type == 8)
			widget.disabledText = buffer.getString();
		if (widget.actionType == 2 || widget.type == 2) {
			widget.optionCircumfix = buffer.getString();
			widget.optionText = buffer.getString();
			widget.optionAttributes = buffer.getUnsignedLEShort();
		}
		if (widget.actionType == 1 || widget.actionType == 4 || widget.actionType == 5 || widget.actionType == 6) {
			widget.tooltip = buffer.getString();
			if (widget.tooltip.length() == 0) {
				if (widget.actionType == 1)
					widget.tooltip = "Ok";
				if (widget.actionType == 4)
					widget.tooltip = "Select";
				if (widget.actionType == 5)
					widget.tooltip = "Select";
				if (widget.actionType == 6)
					widget.tooltip = "Continue";
			}
		}
		return widget;
	}

	public static void load(Archive widgetArchive, TypeFace[] fonts, Archive mediaArchive) {
		spriteCache = new Cache(50000);
		Buffer buffer = new Buffer(widgetArchive.getFile("data"));
		Widget.mediaArchive = mediaArchive;
		Widget.fonts = fonts;
		int parentId = -1;
		int widgetCount = buffer.getUnsignedLEShort();
		interfaces = new Widget[widgetCount];
		data = new byte[widgetCount][];
		while (buffer.currentPosition < buffer.buffer.length) {
			int widgetIndex = buffer.getUnsignedLEShort();
			if (widgetIndex == 65535) {
				parentId = buffer.getUnsignedLEShort();
				widgetIndex = buffer.getUnsignedLEShort();
			}
			int i1 = buffer.currentPosition;
			Widget widget = parse(parentId, buffer, widgetIndex);
			byte temp[] = data[widget.id] = new byte[(buffer.currentPosition - i1) + 2];
			for (int j1 = i1; j1 < buffer.currentPosition; j1++)
				temp[(j1 - i1) + 2] = buffer.buffer[j1];

			temp[0] = (byte) (parentId >> 8);
			temp[1] = (byte) parentId;
		}
		Widget.mediaArchive = null;
	}

	public static void method200(int i) {
		if (i == -1)
			return;
		for (int j = 0; j < interfaces.length; j++)
			if (interfaces[j] != null && interfaces[j].parentId == i && interfaces[j].type != 2)
				interfaces[j] = null;

	}

	public static void setModel(int modelType, Model model, int modelId) {
		modelCache.removeAll();
		if (model != null && modelType != 4)
			modelCache.put(model, (modelType << 16) + modelId);
	}

	public static void reset() {
		interfaces = null;
		mediaArchive = null;
		spriteCache = null;
		fonts = null;
		data = null;
	}

	public Model getAnimatedModel(int frame1Id, int frame2Id, boolean modelEnabled) {
		Model model;
		if (modelEnabled)
			model = getModel(enabledModelType, enabledModelId);
		else
			model = getModel(modelType, modelId);
		if (model == null)
			return null;
		if (frame2Id == -1 && frame1Id == -1 && model.triangleColorValues == null)
			return model;
		Model animatedModel = new Model(true,
				model, Animation.exists(frame2Id) & Animation.exists(frame1Id));
		if (frame2Id != -1 || frame1Id != -1)
			animatedModel.createBones();
		if (frame2Id != -1)
			animatedModel.applyTransform(frame2Id);
		if (frame1Id != -1)
			animatedModel.applyTransform(frame1Id);
		animatedModel.applyLighting(64, 768, -50, -10, -50, true);
		return animatedModel;
	}

}
