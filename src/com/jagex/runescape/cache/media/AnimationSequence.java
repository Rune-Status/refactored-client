package com.jagex.runescape.cache.media;

import com.jagex.runescape.cache.Archive;
import com.jagex.runescape.net.Buffer;
import com.jagex.runescape.media.Animation;

public class AnimationSequence {

	public static int count;
	public static AnimationSequence animations[];
	public int frameCount;
	public int getPrimaryFrame[];
	public int frame1Ids[];
	public int frameLenghts[];
	public int frameStep = -1;
	public int flowControl[];
	public boolean aBoolean300 = false;
	public int anInt301 = 5;
	public int getPlayerShieldDelta = -1;
	public int getPlayerWeaponDelta = -1;
	public int anInt304 = 99;
	public int anInt305 = -1;
	public int priority = -1;
	public int anInt307 = 2;

	public static void load(Archive archive) {
		Buffer buffer = new Buffer(archive.getFile("seq.dat"));
		AnimationSequence.count = buffer.getUnsignedLEShort();
		if (AnimationSequence.animations == null)
			AnimationSequence.animations = new AnimationSequence[AnimationSequence.count];
		for (int animation = 0; animation < count; animation++) {
			if (AnimationSequence.animations[animation] == null)
				AnimationSequence.animations[animation] = new AnimationSequence();
			AnimationSequence.animations[animation].loadDefinition(buffer);
		}
	}

	public int getFrameLength(int animationId) {
		int frameLength = frameLenghts[animationId];
		if (frameLength == 0) {
			Animation animation = Animation.getAnimation(getPrimaryFrame[animationId]);
			if (animation != null)
				frameLength = frameLenghts[animationId] = animation.anInt431;
		}
		if (frameLength == 0)
			frameLength = 1;
		return frameLength;
	}

	public void loadDefinition(Buffer buf) {
		while (true) {
			int attributeId = buf.getUnsignedByte();
			if (attributeId == 0)
				break;
			switch (attributeId) {
				case 1:
					frameCount = buf.getUnsignedByte();
					getPrimaryFrame = new int[frameCount];
					frame1Ids = new int[frameCount];
					frameLenghts = new int[frameCount];
					for (int frame = 0; frame < frameCount; frame++) {
						getPrimaryFrame[frame] = buf.getUnsignedLEShort();
						frame1Ids[frame] = buf.getUnsignedLEShort();
						if (frame1Ids[frame] == 65535)
							frame1Ids[frame] = -1;
						frameLenghts[frame] = buf.getUnsignedLEShort();
					}

					break;
				case 2:
					frameStep = buf.getUnsignedLEShort();
					break;
				case 3:
					int flowCount = buf.getUnsignedByte();
					flowControl = new int[flowCount + 1];
					for (int flow = 0; flow < flowCount; flow++)
						flowControl[flow] = buf.getUnsignedByte();

					flowControl[flowCount] = 0x98967f;
					break;
				case 4:
					aBoolean300 = true;
					break;
				case 5:
					anInt301 = buf.getUnsignedByte();
					break;
				case 6:
					getPlayerShieldDelta = buf.getUnsignedLEShort();
					break;
				case 7:
					getPlayerWeaponDelta = buf.getUnsignedLEShort();
					break;
				case 8:
					anInt304 = buf.getUnsignedByte();
					break;
				case 9:
					anInt305 = buf.getUnsignedByte();
					break;
				case 10:
					priority = buf.getUnsignedByte();
					break;
				case 11:
					anInt307 = buf.getUnsignedByte();
					break;
				case 12:
					buf.getInt(); //dummy
					break;
				default:
					System.out.println("Error unrecognised seq config code: " + attributeId);
					break;
			}
		}
		if (frameCount == 0) {
			frameCount = 1;
			getPrimaryFrame = new int[1];
			getPrimaryFrame[0] = -1;
			frame1Ids = new int[1];
			frame1Ids[0] = -1;
			frameLenghts = new int[1];
			frameLenghts[0] = -1;
		}
		if (anInt305 == -1)
			if (flowControl != null)
				anInt305 = 2;
			else
				anInt305 = 0;
		if (priority == -1) {
			if (flowControl != null) {
				priority = 2;
				return;
			}
			priority = 0;
		}
	}



}
