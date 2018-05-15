package com.jagex.runescape;

import java.applet.AppletContext;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.zip.CRC32;

import com.jagex.runescape.cache.Archive;
import com.jagex.runescape.cache.Index;
import com.jagex.runescape.cache.cfg.ChatCensor;
import com.jagex.runescape.cache.cfg.Varbit;
import com.jagex.runescape.cache.cfg.Varp;
import com.jagex.runescape.cache.def.ActorDefinition;
import com.jagex.runescape.cache.def.FloorDefinition;
import com.jagex.runescape.cache.def.GameObjectDefinition;
import com.jagex.runescape.cache.def.ItemDefinition;
import com.jagex.runescape.cache.media.*;
import com.jagex.runescape.collection.Node;
import com.jagex.runescape.media.Animation;
import com.jagex.runescape.media.ProducingGraphicsBuffer;
import com.jagex.runescape.media.Rasterizer;
import com.jagex.runescape.media.Rasterizer3D;
import com.jagex.runescape.media.renderable.*;
import com.jagex.runescape.media.renderable.actor.Actor;
import com.jagex.runescape.media.renderable.actor.Npc;
import com.jagex.runescape.media.renderable.actor.Player;
import com.jagex.runescape.net.Buffer;
import com.jagex.runescape.net.BufferedConnection;
import com.jagex.runescape.net.ISAACCipher;
import com.jagex.runescape.net.requester.OnDemandNode;
import com.jagex.runescape.net.requester.OnDemandRequester;
import com.jagex.runescape.scene.Region;
import com.jagex.runescape.scene.Scene;
import com.jagex.runescape.scene.SceneSpawnRequest;
import com.jagex.runescape.scene.SpawnObjectNode;
import com.jagex.runescape.scene.tile.FloorDecoration;
import com.jagex.runescape.scene.tile.Wall;
import com.jagex.runescape.scene.tile.WallDecoration;
import com.jagex.runescape.scene.util.CollisionMap;
import com.jagex.runescape.sound.SoundPlayer;
import com.jagex.runescape.sound.SoundTrack;
import com.jagex.runescape.util.*;
import tech.henning.client.Actions;
import tech.henning.client.Configuration;

@SuppressWarnings("serial")
public class Game extends GameShell {


	private final int[] soundVolume = new int[50];
	public int archiveHashes[] = new int[9];
	public byte aByteArrayArray838[][];
	public String reportedName = "";
	public static int anInt841;
	public int anIntArray842[] = { 0xffff00, 0xff0000, 65280, 65535, 0xff00ff, 0xffffff };
	public int anIntArray843[] = new int[SkillConstants.SKILL_COUNT];
	public int anInt844;
	public int anInt845;
	public int anInt846;
	public int anInt847;
	public int anInt848;
	public String friendUsernames[] = new String[200];
	public int anInt850;
	public int anInt851;
	public int anIntArray852[] = new int[5];
	public int anInt853;
	public int anInt854 = 2;
	public int ignoresCount;
	public int coordinates[];
	public int anIntArray857[];
	public int anIntArray858[];
	public int friendsCount;
	public int friendListStatus;
	public String aString861 = "";
	public int anInt862;
	public String aStringArray863[] = new String[100];
	public int anIntArray864[] = new int[100];
	public int anInt865;
	public boolean messagePromptRaised = false;
	public int playerRights;
	public static boolean fps;
	public int packetSize;
	public int opcode;
	public int timeoutCounter;
	public int anInt872;
	public int anInt873;
	public int anInt874;
	public int anInt875;
	public int anInt876;
	public int anInt877;
	public int anInt878;
	public int constructedMapPalette[][][] = new int[4][13][13];
	public IndexedImage aClass50_Sub1_Sub1_Sub3_880;
	public IndexedImage aClass50_Sub1_Sub1_Sub3_881;
	public IndexedImage aClass50_Sub1_Sub1_Sub3_882;
	public IndexedImage aClass50_Sub1_Sub1_Sub3_883;
	public IndexedImage aClass50_Sub1_Sub1_Sub3_884;
	public int anIntArrayArray885[][] = new int[104][104];
	public int anIntArrayArray886[][] = new int[104][104];
	public int privateChatMode;
	public Archive titleArchive;
	public int chunkX;
	public int chunkY;
	public int anIntArrayArrayArray891[][][];
	public boolean aBoolean892 = false;
	public int anInt893;
	public int anInt894 = -992;
	public static int anInt895;
	public ImageRGB cursorCross[] = new ImageRGB[8];
	public int anInt897 = 559;
	public byte aByte898 = 6;
	public ISAACCipher incomingRandom;
	private boolean useJaggrab = Configuration.JAGGRAB_ENABLED;
	public byte aByte901 = -123;
	public long aLong902;
	public int lastOpcode;
	public int secondLastOpcode;
	public int thirdLastOpcode;
	public ProducingGraphicsBuffer aClass18_906;
	public ProducingGraphicsBuffer aClass18_907;
	public ProducingGraphicsBuffer aClass18_908;
	public ProducingGraphicsBuffer aClass18_909;
	public ProducingGraphicsBuffer aClass18_910;
	public ProducingGraphicsBuffer aClass18_911;
	public ProducingGraphicsBuffer aClass18_912;
	public ProducingGraphicsBuffer aClass18_913;
	public ProducingGraphicsBuffer aClass18_914;
	public int anInt915;
	public int anInt916;
	public int anInt917 = 2;
	public boolean aBoolean918 = true;
	public boolean aBoolean919 = true;
	public int anIntArray920[] = new int[151];
	public int anInt921 = 8;
	public int anInt922;
	public static int world = 10;
	public static int portOffset;
	public static boolean memberServer = true;
	public static boolean lowMemory;
	public boolean aBooleanArray927[] = new boolean[5];
	public int anInt928 = -188;
	public Buffer tempBuffer = Buffer.allocate(1);
	public long serverSeed;
	public int anInt931 = 0x23201b;
	public int anInt932 = -1;
	public int anInt933 = -1;
	public boolean aBoolean934;
	public int anInt935 = -1;
	public byte aByte936 = -113;
	public String chatboxInputMessage = "";
	public int anInt938 = -214;
	public int anInt939;
	public int anInt940 = 50;
	public int anIntArray941[] = new int[anInt940];
	public int anIntArray942[] = new int[anInt940];
	public int anIntArray943[] = new int[anInt940];
	public int anIntArray944[] = new int[anInt940];
	public int anIntArray945[] = new int[anInt940];
	public int anIntArray946[] = new int[anInt940];
	public int anIntArray947[] = new int[anInt940];
	public String aStringArray948[] = new String[anInt940];
	public String inputInputMessage = "";
	public boolean aBoolean950 = false;
	public int tickDelta;
	public static int SKILL_EXPERIENCE[];
	public boolean aBoolean953 = false;
	public ImageRGB aClass50_Sub1_Sub1_Sub1Array954[] = new ImageRGB[32];
	public int anInt955;
	public byte aByte956 = 1;
	public String statusLineOne = "";
	public String statusLineTwo = "";
	public boolean aBoolean959 = true;
	public int anInt960 = -1;
	public int thisPlayerServerId = -1;
	public static boolean accountFlagged;
	public static boolean aBoolean963 = true;
	public Buffer outBuffer = Buffer.allocate(1);
	public IndexedImage anIndexedImage1052;
	public IndexedImage anIndexedImage1053;
	public IndexedImage anIndexedImage1054;
	public int anInt968 = 2048;
	public int thisPlayerId = 2047;
	public Player players[] = new Player[anInt968];
	public int localPlayerCount;
	public int playerList[] = new int[anInt968];
	public int updatedPlayerCount;
	public int updatedPlayers[] = new int[anInt968];
	public Buffer cachedAppearances[] = new Buffer[anInt968];
	public IndexedImage tabIcon[] = new IndexedImage[13];
	public int anInt977;
	public static int anInt978;
	public int firstMenuOperand[] = new int[500];
	public int secondMenuOperand[] = new int[500];
	public int menuActionTypes[] = new int[500];
	public int selectedMenuActions[] = new int[500];
	public IndexedImage aClass50_Sub1_Sub1_Sub3_983;
	public IndexedImage aClass50_Sub1_Sub1_Sub3_984;
	public IndexedImage aClass50_Sub1_Sub1_Sub3_985;
	public IndexedImage aClass50_Sub1_Sub1_Sub3_986;
	public IndexedImage aClass50_Sub1_Sub1_Sub3_987;
	public int backDialogueId = -1;
	public int placementX;
	public int placementY;
	public int anIntArray991[] = new int[5];
	public int anInt992;
	public int anInt993;
	public int anInt994;
	public int anInt995;
	public int anInt996;
	public int anInt997;
	public int anInt998;
	public static boolean aBoolean999;
	public int chatboxLineOffsets[];
	public int anIntArray1001[];
	public int anIntArray1002[];
	public int anIntArray1003[];
	public int anInt1004;
	public int anIntArray1005[] = new int[2000];
	public int publicChatMode;
	public static final int playerColours[][] = {
			{ 6798, 107, 10283, 16, 4797, 7744, 5799, 4634, 33697, 22433, 2983, 54193 },
			{ 8741, 12, 64030, 43162, 7735, 8404, 1701, 38430, 24094, 10153, 56621, 4783, 1341, 16578, 35003, 25239 },
			{ 25238, 8742, 12, 64030, 43162, 7735, 8404, 1701, 38430, 24094, 10153, 56621, 4783, 1341, 16578, 35003 },
			{ 4626, 11146, 6439, 12, 4758, 10270 }, { 4550, 4537, 5681, 5673, 5790, 6806, 8076, 4574 } };
	public int anInt1009;
	public int anInt1010 = 2;
	public int anInt1011;
	public int anInt1012;
	public static int anInt1013;
	public boolean aBoolean1014 = false;
	public int anInt1015;
	public boolean aBoolean1016 = false;
	public ImageRGB anImageRGB1226;
	public ImageRGB anImageRGB1227;
	public int anIntArray1019[] = new int[151];
	public int anInt1020;
	public int anInt1021;
	public int crossIndex;
	public int crossType;
	public BufferedConnection gameConnection;
	public String chatMessage = "";
	public String aString1027;
	public boolean aBoolean1028 = false;
	public int anIntArray1029[] = new int[SkillConstants.SKILL_COUNT];
	public int anInt1030;
	public ImageRGB worldMapHintIcons[] = new ImageRGB[100];
	public final int anIntArray1032[] = { 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3 };
	public boolean aBoolean1033 = false;
	public int anInt1034;
	public int currentSound;
	public ImageRGB mapFlagMarker;
	public ImageRGB aClass50_Sub1_Sub1_Sub1_1037;
	public boolean aBoolean1038 = true;
	public int widgetSettings[] = new int[2000];
	public int nextTopLeftTileX;
	public int nextTopRightTileY;
	public int topLeftTileX;
	public int topLeftTileY;
	public int anInt1044;
	public int anInt1045;
	public boolean aBoolean1046 = false;
	public int anInt1047;
	public int anInt1048;
	public static int anInt1049;
	public int minimapState;
	public int anInt1051 = 69;
	public static int anInt1052;
	public int anInt1053 = -1;
	public int anIntArray1054[] = new int[SkillConstants.SKILL_COUNT];
	public int anInt1055 = 2;
	public int anInt1056 = 3;
	public int systemUpdateTime;
	public String clickToContinueString;
	public TypeFace fontSmall;
	public TypeFace fontNormal;
	public TypeFace fontBold;
	public TypeFace fontFancy;
	public int mouseInvInterfaceIndex;
	public int lastActiveInvInterface;
	public boolean menuOpen = false;
	public byte aByte1066 = 1;
	public boolean aBoolean1067 = false;
	public int playerMembers;
	public String aStringArray1069[] = new String[5];
	public boolean aBooleanArray1070[] = new boolean[5];
	public int loadingStage;
	public int anInt1072 = 20411;
	public long ignores[] = new long[100];
	public boolean aBoolean1074;
	public int anInt1075;
	public int minimapHintCount;
	public int minimapHintX[] = new int[1000];
	public int minimapHintY[] = new int[1000];
	public ImageRGB aClass50_Sub1_Sub1_Sub1Array1079[] = new ImageRGB[32];
	public int anInt1080 = 0x4d4233;
	public int anIntArray1081[] = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
	public static int anInt1082;
	public int anInt1083;
	public int anIntArray1084[];
	public int anIntArray1085[];
	public ImageRGB aClass50_Sub1_Sub1_Sub1_1086;
	public int anInt1087;
	public CRC32 archiveCrc = new CRC32();
	public int anInt1089 = -1;
	public int sound[] = new int[50];
	public int plane;
	public String username = "Promises";
	public String password = "Testing";
	public int anInt1094;
	public IndexedImage scrollbarUp;
	public IndexedImage scrollbarDown;
	public boolean aBoolean1097 = false;
	public boolean reportMutePlayer = false;
	public int characterEditColors[] = new int[5];
	public static int anInt1100;
	public int anInt1101;
	public ImageRGB aClass50_Sub1_Sub1_Sub1_1102;
	public ImageRGB aClass50_Sub1_Sub1_Sub1_1103;
	public String chatboxInput = "";
	public int anIntArray1105[] = new int[5];
	public int anInt1106;
	public int anInt1107 = 78;
	public ProducingGraphicsBuffer aClass18_1108;
	public ProducingGraphicsBuffer aClass18_1109;
	public ProducingGraphicsBuffer aClass18_1110;
	public int modifiedWidgetId;
	public int selectedInventorySlot;
	public int activeInterfaceType;
	public int anInt1114;
	public int anInt1115;
	public ImageRGB minimapCompass;
	public IndexedImage titleFlameEmblem[];
	public int anInt1118;
	public int anInt1119 = -30658;
	public int destinationX;
	public int destinationY;
	public ImageRGB minimapImage;
	public int anIntArray1123[] = new int[4000];
	public int anIntArray1124[] = new int[4000];
	public byte currentSceneTileFlags[][][];
	public int anInt1126;
	public boolean aBoolean1127 = false;
	public int previousSong;
	public int anInt1129;
	public long friends[] = new long[200];
	public Buffer chatBuffer = new Buffer(new byte[5000]);
	public Npc npcs[] = new Npc[16384];
	public int anInt1133;
	public int anIntArray1134[] = new int[16384];
	public int anInt1135 = 0x766654;
	public boolean aBoolean1136 = false;
	public boolean loggedIn = false;
	public int anInt1138;
	public static int anInt1139;
	public int anInt1140 = -110;
	public long aLong1141;
	public IndexedImage moderatorIcon[] = new IndexedImage[2];
	public byte aByte1143 = -80;
	public boolean characterEditChangeGenger = true;
	public int quakeTimes[] = new int[5];
	public int itemSelected;
	public int anInt1147;
	public int anInt1148;
	public int anInt1149;
	public String aString1150;
	public int anInt1151;
	public int anInt1152;
	public IndexedImage aClass50_Sub1_Sub1_Sub3Array1153[] = new IndexedImage[100];
	public int anInt1154 = -916;
	public boolean aBoolean1155 = false;
	public ProducingGraphicsBuffer aClass18_1156;
	public ProducingGraphicsBuffer aClass18_1157;
	public ProducingGraphicsBuffer aClass18_1158;
	public ProducingGraphicsBuffer chatboxProducingGraphicsBuffer;
	public static int anInt1160;
	public byte aByte1161 = 97;
	public static int anInt1162;
	public boolean aBoolean1163 = false;
	public Scene currentScene;
	public static int anInt1165;
	public int anIntArray1166[] = new int[256];
	public static Player localPlayer;
	public static int anInt1168;
	public int openInterfaceId = -1;
	public int anInt1170;
	public int widgetSelected;
	public int anInt1172;
	public int anInt1173;
	public String selectedWidgetName;
	public int anInt1175 = -89;
	public int anIntArray1176[];
	public int anIntArray1177[];
	public int anInt1178 = 300;
	public int anInt1257;
	public int anIntArray1180[] = new int[33];
	public boolean redrawTabArea = false;
	public ImageRGB aClass50_Sub1_Sub1_Sub1Array1182[] = new ImageRGB[20];
	public int menuActionRow;
	public String menuActionTexts[] = new String[500];
	public IndexedImage inventoryBackgroundImage;
	public IndexedImage minimapBackgroundImage;
	public IndexedImage chatboxBackgroundImage;
	public Buffer buffer = Buffer.allocate(1);
	public int cost[][] = new int[104][104];
	public static boolean aBoolean1190 = true;
	public int dialogueId = -1;
	public ImageRGB mapdotItem;
	public ImageRGB mapdotActor;
	public ImageRGB mapdotPlayer;
	public ImageRGB mapdotFriend;
	public ImageRGB mapdotTeammate;
	public int anInt1197;
	public ProducingGraphicsBuffer aClass18_1198;
	public ProducingGraphicsBuffer aClass18_1199;
	public ProducingGraphicsBuffer aClass18_1200;
	public ProducingGraphicsBuffer flameLeftBackground;
	public ProducingGraphicsBuffer flameRightBackground;
	public ProducingGraphicsBuffer aClass18_1203;
	public ProducingGraphicsBuffer aClass18_1204;
	public ProducingGraphicsBuffer aClass18_1205;
	public ProducingGraphicsBuffer aClass18_1206;
	public static boolean aBoolean1207;
	public int anInt1208;
	public boolean aBoolean1209 = false;
	public LinkedList aClass6_1210 = new LinkedList();
	public boolean oriented = false;
	public boolean aBoolean1212 = false;
	public int anInt1213 = -1;
	public static int BITFIELD_MAX_VALUE[];
	public int anInt1215;
	public int cameraX;
	public int cameraZ;
	public int cameraY;
	public int anInt1219;
	public int anInt1220;
	public int friendsListAction;
	public int anInt1222;
	public int anInt1223;
	public Socket jaggrabSocket;
	public int loginScreenState;
	public int anInt1226;
	public int tradeMode;
	public Index stores[] = new Index[5];
	public long aLong1229;
	public static int anInt1230;
	public int reportAbuseInterfaceID = -1;
	public byte aByteArrayArray1232[][];
	public int anInt1233;
	public int anInt1234 = 1;
	public static int anInt1235;
	public int anInt1236 = 326;
	public static int anInt1237;
	public int anInt1238;
	public boolean aBoolean1239 = false;
	public boolean redrawChatbox = false;
	public int lastAddress;
	public static boolean aBoolean1242 = true;
	public volatile boolean aBoolean1243 = false;
	public int inputType;
	public byte aByteArray1245[] = new byte[16384];
	public boolean inTutorialIsland;
	public ImageRGB minimapEdge;
	public MouseCapturer mouseCapturer;
	public Widget aClass13_1249 = new Widget();
	public long aLong1172;
	public int anInt1251 = 128;
	public int cameraHorizontal;
	public int anInt1186;
	public int anInt1187;
	public int anInt1255;
	public int anInt1256 = 1;
	public int anIntArray1258[] = new int[100];
	public int soundDelay[] = new int[50];
	public CollisionMap currentCollisionMap[] = new CollisionMap[4];
	public LinkedList aClass6_1261 = new LinkedList();
	public int anInt1262;
	public int anInt1263;
	public int anInt1264;
	public boolean aBoolean1265 = false;
	public boolean musicEnabled = true;
	public int friendWorlds[] = new int[200];
	public static final int SKIN_COLOURS[] = { 9104, 10275, 7595, 3610, 7975, 8526, 918, 38802, 24466, 10145, 58654,
			5027, 1457, 16565, 34991, 25486 };
	public int anInt1269;
	public int nextSong;
	public boolean songChanging = true;
	public int anInt1272 = -1;
	public int anInt1273;
	public boolean aBoolean1274 = true;
	public boolean aBoolean1275 = true;
	public int anInt1276 = -1;
	public boolean aBoolean1277 = false;
	public ImageRGB minimapHint[] = new ImageRGB[1000];
	public int anInt1279 = -1;
	public int anInt1280;
	public int anInt1281 = -939;
	public LinkedList aClass6_1282 = new LinkedList();
	public boolean aBoolean1283 = false;
	public int anInt1284;
	public int anInt1285 = 3;
	public int anIntArray1286[] = new int[33];
	public int anInt1287 = 0x332d25;
	public ImageRGB aClass50_Sub1_Sub1_Sub1Array1288[] = new ImageRGB[32];
	public int anInt1289;
	public int anIntArray1290[] = { 17, 24, 34, 40 };
	public OnDemandRequester onDemandRequester;
	public IndexedImage titleboxImage;
	public IndexedImage titleboxButtonImage;
	public int removePlayerCount;
	public int removePlayers[] = new int[1000];
	public int chatTypes[] = new int[100];
	public String chatPlayerNames[] = new String[100];
	public String chatMessages[] = new String[100];
	public int duplicateClickCount;
	public int anInt1300;
	public boolean aBoolean1301 = true;
	public int anInt1302;
	public int anInt1303;
	public int anInt1304;
	public int menuClickX;
	public int menuClickY;
	public int anInt1307;
	public int anInt1308;
	public static int anInt1309;
	public int anIntArray1310[];
	public int anIntArray1311[];
	public int anIntArray1312[];
	public int anIntArray1313[];
	public volatile boolean aBoolean1314 = false;
	public int anInt1315;
	public byte aByte1317 = -58;
	public int anInt1318 = 416;
	public int anInt1319;
	public volatile boolean aBoolean1320 = false;
	public int soundType[] = new int[50];
	public int anInt1322;
	public LinkedList groundItems[][][] = new LinkedList[4][104][104];
	public int anInt1324;
	public static int pulseCycle;
	public int characterEditIdentityKits[] = new int[7];
	public int currentSong = -1;
	public int anInt1328 = 409;
	public int atInventoryLoopCycle;
	public int anInt1330;
	public int anInt1331;
	public int atInventoryInterfaceType;
	public static String VALID_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"\243$%^&*()-_=+[{]};:'@#~,<.>/?\\| ";

	static {
		SKILL_EXPERIENCE = new int[99];
		int value = 0;
		for (int level = 0; level < 99; level++) {
			int realLevel = level + 1;
			int expDiff = (int) ((double) realLevel + 300D * Math.pow(2D, (double) realLevel / 7D));
			value += expDiff;
			SKILL_EXPERIENCE[level] = value / 4;
		}

		BITFIELD_MAX_VALUE = new int[32];
		value = 2;
		for (int k = 0; k < 32; k++) {
			BITFIELD_MAX_VALUE[k] = value - 1;
			value += value;
		}

	}

	public static String getCombatLevelColour(int user, int opponent) {
		int difference = user - opponent;
		if (difference < -9)
			return "@red@";
		if (difference < -6)
			return "@or3@";
		if (difference < -3)
			return "@or2@";
		if (difference < 0)
			return "@or1@";
		if (difference > 9)
			return "@gre@";
		if (difference > 6)
			return "@gr3@";
		if (difference > 3)
			return "@gr2@";
		if (difference > 0)
			return "@gr1@";
		else
			return "@yel@";
	}

	public static void main(String args[]) {
		try {
			System.out.println("RS2 user client - release #" + 377);
			world = 0;
			portOffset = 0;
			setHighMemory();
			memberServer = true;
			SignLink.storeId = 32;
			SignLink.initialize(InetAddress.getLocalHost());
			Game cl = new Game();
			cl.initializeApplication(765, 503);
			return;
		} catch (Exception exception) {
			return;
		}
	}

	public static void setHighMemory() {
		Scene.lowMemory = false;
		Rasterizer3D.lowMemory = false;
		lowMemory = false;
		Region.lowMemory = false;
		GameObjectDefinition.lowMemory = false;
	}

	public static void setLowMemory() {
		Scene.lowMemory = true;
		Rasterizer3D.lowMemory = true;
		lowMemory = true;
		Region.lowMemory = true;
		GameObjectDefinition.lowMemory = true;
	}

	public static String getFullAmountText(int amount) {
		String string = String.valueOf(amount);
		for (int index = string.length() - 3; index > 0; index -= 3)
			string = string.substring(0, index) + "," + string.substring(index);

		if (string.length() > 8)
			string = "@gre@" + string.substring(0, string.length() - 8) + " million @whi@(" + string + ")";
		else if (string.length() > 4)
			string = "@cya@" + string.substring(0, string.length() - 4) + "K @whi@(" + string + ")";
		return " " + string;
	}

	public static String getShortenedAmountText(int coins) {
		if (coins < 0x186a0)
			return String.valueOf(coins);
		if (coins < 0x989680)
			return coins / 1000 + "K";
		else
			return coins / 0xf4240 + "M";
	}

	public void addChatMessage(String name, String message, int type) {
		if (type == 0 && dialogueId != -1) {
			clickToContinueString = message;
			super.clickType = 0;
		}
		if (backDialogueId == -1)
			redrawChatbox = true;
		for (int index = 99; index > 0; index--) {
			chatTypes[index] = chatTypes[index - 1];
			chatPlayerNames[index] = chatPlayerNames[index - 1];
			chatMessages[index] = chatMessages[index - 1];
		}

		chatTypes[0] = type;
		chatPlayerNames[0] = name;
		chatMessages[0] = message;
	}

	public void addFriend(long name) {
		try {
			if (name == 0L)
				return;
			if (friendsCount >= 100 && playerMembers != 1) {
				addChatMessage("", "Your friendlist is full. Max of 100 for free users, and 200 for members", 0);
				return;
			}
			if (friendsCount >= 200) {
				addChatMessage("", "Your friendlist is full. Max of 100 for free users, and 200 for members", 0);
				return;
			}
			String username = TextUtils.formatName(TextUtils.longToName(name));
			for (int index = 0; index < friendsCount; index++)
				if (friends[index] == name) {
					addChatMessage("", username + " is already on your friend list", 0);
					return;
				}

			for (int index = 0; index < ignoresCount; index++)
				if (ignores[index] == name) {
					addChatMessage("", "Please remove " + username + " from your ignore list first", 0);
					return;
				}

			if (username.equals(localPlayer.playerName))
				return;
			friendUsernames[friendsCount] = username;
			friends[friendsCount] = name;
			friendWorlds[friendsCount] = 0;
			friendsCount++;
			redrawTabArea = true;
			outBuffer.putOpcode(120);
			outBuffer.putLong(name);
			return;
		} catch (RuntimeException runtimeexception) {
			SignLink.reportError("94629, " + name + ", " + ", " + runtimeexception.toString());
		}
		throw new RuntimeException();
	}

	public void addIgnore(int i, long name) {
		try {
			if (name == 0L)
				return;
			if (ignoresCount >= 100) {
				addChatMessage("", "Your ignore list is full. Max of 100 hit", 0);
				return;
			}
			String username = TextUtils.formatName(TextUtils.longToName(name));
			for (int index = 0; index < ignoresCount; index++)
				if (ignores[index] == name) {
					addChatMessage("", username + " is already on your ignore list", 0);
					return;
				}

			for (int index = 0; index < friendsCount; index++)
				if (friends[index] == name) {
					addChatMessage("", "Please remove " + username + " from your friend list first", 0);
					return;
				}

			ignores[ignoresCount++] = name;
			redrawTabArea = true;
			outBuffer.putOpcode(217);
			outBuffer.putLong(name);
			return;
		} catch (RuntimeException runtimeexception) {
			SignLink.reportError("27939, " + i + ", " + name + ", " + runtimeexception.toString());
		}
		throw new RuntimeException();
	}

	public void adjustMidiVolume(boolean flag, byte byte0, int volume) {
		SignLink.midiVolume = volume;
		if (flag)
			SignLink.midi = "voladjust";
	}

	public void method14(String s, int i) {
		if (s == null || s.length() == 0) {
			anInt862 = 0;
			return;
		}
		String s1 = s;
		String as[] = new String[100];
		int j = 0;
		do {
			int k = s1.indexOf(" ");
			if (k == -1)
				break;
			String s2 = s1.substring(0, k).trim();
			if (s2.length() > 0)
				as[j++] = s2.toLowerCase();
			s1 = s1.substring(k + 1);
		} while (true);
		s1 = s1.trim();
		if (s1.length() > 0)
			as[j++] = s1.toLowerCase();
		anInt862 = 0;
		if (i != 2)
			aBoolean959 = !aBoolean959;
		label0: for (int l = 0; l < ItemDefinition.count; l++) {
			ItemDefinition class16 = ItemDefinition.lookup(l);
			if (class16.notedTemplateId != -1 || class16.name == null)
				continue;
			String s3 = class16.name.toLowerCase();
			for (int i1 = 0; i1 < j; i1++)
				if (s3.indexOf(as[i1]) == -1)
					continue label0;

			aStringArray863[anInt862] = s3;
			anIntArray864[anInt862] = l;
			anInt862++;
			if (anInt862 >= aStringArray863.length)
				return;
		}

	}

	public void closeWidgets() {
		outBuffer.putOpcode(110);
		if (anInt1089 != -1) {
			method44(aBoolean1190, anInt1089);
			anInt1089 = -1;
			redrawTabArea = true;
			aBoolean1239 = false;
			aBoolean950 = true;
		}
		if (backDialogueId != -1) {
			method44(aBoolean1190, backDialogueId);
			backDialogueId = -1;
			redrawChatbox = true;
			aBoolean1239 = false;
		}
		if (anInt1053 != -1) {
			method44(aBoolean1190, anInt1053);
			anInt1053 = -1;
			aBoolean1046 = true;
		}
		if (anInt960 != -1) {
			method44(aBoolean1190, anInt960);
			anInt960 = -1;
		}
		if (openInterfaceId != -1) {
			method44(aBoolean1190, openInterfaceId);
			openInterfaceId = -1;
		}
	}

	private void addNewPlayers(int size, Buffer buffer) {
		while (buffer.bitPosition + 10 < size * 8) {
			int id = buffer.getBits(11);

			if (id == 2047)
				break;

			if (players[id] == null) {
				players[id] = new Player();

				if (cachedAppearances[id] != null)
					players[id].updateAppearance(cachedAppearances[id]);
			}

			playerList[localPlayerCount++] = id;
			Player player = players[id];
			player.pulseCycle = pulseCycle;
			int x = buffer.getBits(5);

			if (x > 15)
				x -= 32;

			int updated = buffer.getBits(1);

			if (updated == 1)
				updatedPlayers[updatedPlayerCount++] = id;

			int discardQueue = buffer.getBits(1);
			int y = buffer.getBits(5);

			if (y > 15)
				y -= 32;

			player.setPosition(localPlayer.pathX[0] + x, localPlayer.pathY[0] + y, discardQueue == 1);
		}

		buffer.finishBitAccess();
	}

	private void processFlamesCycle() {
		aBoolean1320 = true;

		try {
			long startTime = System.currentTimeMillis();
			int cycle = 0;
			int interval = 20;

			while (aBoolean1243) {
				anInt1101++;

				calculateFlamePositions();
				calculateFlamePositions();
				renderFlames();

				if (++cycle > 10) {
					long currentTime = System.currentTimeMillis();
					int difference = (int) (currentTime - startTime) / 10 - interval;
					interval = 40 - difference;

					if (interval < 5)
						interval = 5;

					cycle = 0;
					startTime = currentTime;
				}
				try {
					Thread.sleep(interval);
				} catch (Exception ignored) {}
			}
		} catch (Exception ignored) {}

		aBoolean1320 = false;
	}

	public void method18(byte byte0) {
		if (byte0 != 3)
			return;
		for (SpawnObjectNode spawnObjectNode = (SpawnObjectNode) aClass6_1261.first(); spawnObjectNode != null; spawnObjectNode = (SpawnObjectNode) aClass6_1261
				.next())
			if (spawnObjectNode.anInt1390 == -1) {
				spawnObjectNode.anInt1395 = 0;
				method140((byte) -61, spawnObjectNode);
			} else {
				spawnObjectNode.remove();
			}

	}

	public void method19(String s) {
		System.out.println(s);
		try {
			getAppletContext().showDocument(new URL(getCodeBase(), "loaderror_" + s + ".html"));
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		do
			try {
				Thread.sleep(1000L);
			} catch (Exception _ex) {
			}
		while (true);
	}

	public void shutdown() {
		players = null;
		playerList = null;
		updatedPlayers = null;
		cachedAppearances = null;
		removePlayers = null;
		aClass18_906 = null;
		aClass18_907 = null;
		aClass18_908 = null;
		aClass18_909 = null;
		aClass50_Sub1_Sub1_Sub3_880 = null;
		aClass50_Sub1_Sub1_Sub3_881 = null;
		aClass50_Sub1_Sub1_Sub3_882 = null;
		aClass50_Sub1_Sub1_Sub3_883 = null;
		aClass50_Sub1_Sub1_Sub3_884 = null;
		aClass50_Sub1_Sub1_Sub3_983 = null;
		aClass50_Sub1_Sub1_Sub3_984 = null;
		aClass50_Sub1_Sub1_Sub3_985 = null;
		aClass50_Sub1_Sub1_Sub3_986 = null;
		aClass50_Sub1_Sub1_Sub3_987 = null;
		friendUsernames = null;
		friends = null;
		friendWorlds = null;
		aClass18_1108 = null;
		aClass18_1109 = null;
		aClass18_1110 = null;
		widgetSettings = null;
		coordinates = null;
		aByteArrayArray838 = null;
		aByteArrayArray1232 = null;
		anIntArray857 = null;
		anIntArray858 = null;
		aClass18_1203 = null;
		aClass18_1204 = null;
		aClass18_1205 = null;
		aClass18_1206 = null;
		anIntArrayArray885 = null;
		cost = null;
		anIntArray1123 = null;
		anIntArray1124 = null;
		mapdotItem = null;
		mapdotActor = null;
		mapdotPlayer = null;
		mapdotFriend = null;
		mapdotTeammate = null;
		if (mouseCapturer != null)
			mouseCapturer.capturing = false;
		mouseCapturer = null;
		anIndexedImage1052 = null;
		anIndexedImage1053 = null;
		anIndexedImage1054 = null;
		aClass18_910 = null;
		aClass18_911 = null;
		aClass18_912 = null;
		aClass18_913 = null;
		aClass18_914 = null;
		anIntArrayArrayArray891 = null;
		currentSceneTileFlags = null;
		currentScene = null;
		currentCollisionMap = null;
		minimapImage = null;
		flameLeftBackground = null;
		flameRightBackground = null;
		aClass18_1198 = null;
		aClass18_1199 = null;
		aClass18_1200 = null;
		minimapCompass = null;
		aClass50_Sub1_Sub1_Sub1Array1182 = null;
		aClass50_Sub1_Sub1_Sub1Array1288 = null;
		aClass50_Sub1_Sub1_Sub1Array1079 = null;
		aClass50_Sub1_Sub1_Sub1Array954 = null;
		cursorCross = null;
		stopMidi();
		outBuffer = null;
		tempBuffer = null;
		buffer = null;
		aClass18_1156 = null;
		aClass18_1157 = null;
		aClass18_1158 = null;
		chatboxProducingGraphicsBuffer = null;
		inventoryBackgroundImage = null;
		minimapBackgroundImage = null;
		chatboxBackgroundImage = null;
		try {
			if (gameConnection != null)
				gameConnection.close();
		} catch (Exception _ex) {
		}
		gameConnection = null;
		minimapHintX = null;
		minimapHintY = null;
		minimapHint = null;
		npcs = null;
		anIntArray1134 = null;
		aByteArray1245 = null;
		chatBuffer = null;
		aClass50_Sub1_Sub1_Sub3Array1153 = null;
		worldMapHintIcons = null;
		anIntArrayArray886 = null;
		tabIcon = null;
		aClass6_1282 = null;
		aClass6_1210 = null;
		aClass50_Sub1_Sub1_Sub1_1086 = null;
		if (onDemandRequester != null)
			onDemandRequester.stop();
		onDemandRequester = null;
		firstMenuOperand = null;
		secondMenuOperand = null;
		menuActionTypes = null;
		selectedMenuActions = null;
		menuActionTexts = null;
		groundItems = null;
		aClass6_1261 = null;
		method141();
		GameObjectDefinition.method433(false);
		ActorDefinition.reset();
		ItemDefinition.dispose();
		Widget.reset();
		FloorDefinition.cache = null;
		IdentityKit.cache = null;
		AnimationSequence.animations = null;
		SpotAnimation.cache = null;
		SpotAnimation.modelCache = null;
		Varp.cache = null;
		super.imageProducer = null;
		Player.modelCache = null;
		Rasterizer3D.reset();
		Scene.method240();
		Model.reset();
		Animation.reset();
		System.gc();
	}

	public void method21(boolean flag) {
		if (flag)
			return;
		if (super.clickType == 1) {
			if (super.clickX >= 539 && super.clickX <= 573 && super.clickY >= 169 && super.clickY < 205
					&& anIntArray1081[0] != -1) {
				redrawTabArea = true;
				anInt1285 = 0;
				aBoolean950 = true;
			}
			if (super.clickX >= 569 && super.clickX <= 599 && super.clickY >= 168 && super.clickY < 205
					&& anIntArray1081[1] != -1) {
				redrawTabArea = true;
				anInt1285 = 1;
				aBoolean950 = true;
			}
			if (super.clickX >= 597 && super.clickX <= 627 && super.clickY >= 168 && super.clickY < 205
					&& anIntArray1081[2] != -1) {
				redrawTabArea = true;
				anInt1285 = 2;
				aBoolean950 = true;
			}
			if (super.clickX >= 625 && super.clickX <= 669 && super.clickY >= 168 && super.clickY < 203
					&& anIntArray1081[3] != -1) {
				redrawTabArea = true;
				anInt1285 = 3;
				aBoolean950 = true;
			}
			if (super.clickX >= 666 && super.clickX <= 696 && super.clickY >= 168 && super.clickY < 205
					&& anIntArray1081[4] != -1) {
				redrawTabArea = true;
				anInt1285 = 4;
				aBoolean950 = true;
			}
			if (super.clickX >= 694 && super.clickX <= 724 && super.clickY >= 168 && super.clickY < 205
					&& anIntArray1081[5] != -1) {
				redrawTabArea = true;
				anInt1285 = 5;
				aBoolean950 = true;
			}
			if (super.clickX >= 722 && super.clickX <= 756 && super.clickY >= 169 && super.clickY < 205
					&& anIntArray1081[6] != -1) {
				redrawTabArea = true;
				anInt1285 = 6;
				aBoolean950 = true;
			}
			if (super.clickX >= 540 && super.clickX <= 574 && super.clickY >= 466 && super.clickY < 502
					&& anIntArray1081[7] != -1) {
				redrawTabArea = true;
				anInt1285 = 7;
				aBoolean950 = true;
			}
			if (super.clickX >= 572 && super.clickX <= 602 && super.clickY >= 466 && super.clickY < 503
					&& anIntArray1081[8] != -1) {
				redrawTabArea = true;
				anInt1285 = 8;
				aBoolean950 = true;
			}
			if (super.clickX >= 599 && super.clickX <= 629 && super.clickY >= 466 && super.clickY < 503
					&& anIntArray1081[9] != -1) {
				redrawTabArea = true;
				anInt1285 = 9;
				aBoolean950 = true;
			}
			if (super.clickX >= 627 && super.clickX <= 671 && super.clickY >= 467 && super.clickY < 502
					&& anIntArray1081[10] != -1) {
				redrawTabArea = true;
				anInt1285 = 10;
				aBoolean950 = true;
			}
			if (super.clickX >= 669 && super.clickX <= 699 && super.clickY >= 466 && super.clickY < 503
					&& anIntArray1081[11] != -1) {
				redrawTabArea = true;
				anInt1285 = 11;
				aBoolean950 = true;
			}
			if (super.clickX >= 696 && super.clickX <= 726 && super.clickY >= 466 && super.clickY < 503
					&& anIntArray1081[12] != -1) {
				redrawTabArea = true;
				anInt1285 = 12;
				aBoolean950 = true;
			}
			if (super.clickX >= 724 && super.clickX <= 758 && super.clickY >= 466 && super.clickY < 502
					&& anIntArray1081[13] != -1) {
				redrawTabArea = true;
				anInt1285 = 13;
				aBoolean950 = true;
			}
		}
	}

	void mouseWheelDragged(int i, int j) {
		if (!mouseWheelDown)
			return;
		this.anInt1186 += i * 3;
		this.anInt1187 += (j << 1);
	}

	public void checkForGameUsages(int i) {
		i = 61 / i;
		try {
			int j = localPlayer.worldX + anInt853;
			int k = localPlayer.worldY + anInt1009;
			if (anInt1262 - j < -500 || anInt1262 - j > 500 || anInt1263 - k < -500 || anInt1263 - k > 500) {
				anInt1262 = j;
				anInt1263 = k;
			}
			if (anInt1262 != j)
				anInt1262 += (j - anInt1262) / 16;
			if (anInt1263 != k)
				anInt1263 += (k - anInt1263) / 16;
			if (super.keyStatus[1] == 1)
				anInt1186 += (-24 - anInt1186) / 2;
			else if (super.keyStatus[2] == 1)
				anInt1186 += (24 - anInt1186) / 2;
			else
				anInt1186 /= 2;
			if (super.keyStatus[3] == 1)
				anInt1187 += (12 - anInt1187) / 2;
			else if (super.keyStatus[4] == 1)
				anInt1187 += (-12 - anInt1187) / 2;
			else
				anInt1187 /= 2;
			cameraHorizontal = cameraHorizontal + anInt1186 / 2 & 0x7ff;
			anInt1251 += anInt1187 / 2;
			if (anInt1251 < 128)
				anInt1251 = 128;
			if (anInt1251 > 383)
				anInt1251 = 383;
			int l = anInt1262 >> 7;
			int i1 = anInt1263 >> 7;
			int j1 = method110(anInt1263, anInt1262, (byte) 9, plane);
			int k1 = 0;
			if (l > 3 && i1 > 3 && l < 100 && i1 < 100) {
				for (int l1 = l - 4; l1 <= l + 4; l1++) {
					for (int j2 = i1 - 4; j2 <= i1 + 4; j2++) {
						int k2 = plane;
						if (k2 < 3 && (currentSceneTileFlags[1][l1][j2] & 2) == 2)
							k2++;
						int l2 = j1 - anIntArrayArrayArray891[k2][l1][j2];
						if (l2 > k1)
							k1 = l2;
					}

				}

			}
			int i2 = k1 * 192;
			if (i2 > 0x17f00)
				i2 = 0x17f00;
			if (i2 < 32768)
				i2 = 32768;
			if (i2 > anInt1289) {
				anInt1289 += (i2 - anInt1289) / 24;
				return;
			}
			if (i2 < anInt1289) {
				anInt1289 += (i2 - anInt1289) / 80;
				return;
			}
		} catch (Exception _ex) {
			SignLink.reportError("glfc_ex " + localPlayer.worldX + ","
					+ localPlayer.worldY + "," + anInt1262 + "," + anInt1263 + "," + chunkX + ","
					+ chunkY + "," + nextTopLeftTileX + "," + nextTopRightTileY);
			throw new RuntimeException("eek");
		}
	}

	public boolean processFriendListClick(Widget widget) {
		int row = widget.contentType;
		if (row >= 1 && row <= 200 || row >= 701 && row <= 900) {
			if (row >= 801)
				row -= 701;
			else if (row >= 701)
				row -= 601;
			else if (row >= 101)
				row -= 101;
			else
				row--;
			menuActionTexts[menuActionRow] = "Remove @whi@" + friendUsernames[row];
			menuActionTypes[menuActionRow] = Actions.REMOVE_FRIEND;
			menuActionRow++;
			menuActionTexts[menuActionRow] = "Message @whi@" + friendUsernames[row];
			menuActionTypes[menuActionRow] = Actions.PRIVATE_MESSAGE;
			menuActionRow++;
			return true;
		}
		if (row >= 401 && row <= 500) {
			menuActionTexts[menuActionRow] = "Remove @whi@" + widget.disabledText;
			menuActionTypes[menuActionRow] = Actions.REMOVE_FRIEND;
			menuActionRow++;
			return true;
		} else {
			return false;
		}
	}

	public void method24(boolean flag, byte abyte0[], int i) {
		if (!musicEnabled) {
			return;
		} else {
			SignLink.fadeMidi = flag ? 1 : 0;
			SignLink.saveMidi(abyte0, abyte0.length);
			i = 71 / i;
			return;
		}
	}

	public void method25() {
		aBoolean1277 = true;
		for (int j = 0; j < 7; j++) {
			characterEditIdentityKits[j] = -1;
			for (int k = 0; k < IdentityKit.count; k++) {
				if (IdentityKit.cache[k].widgetDisplayed
						|| IdentityKit.cache[k].partId != j + (characterEditChangeGenger ? 0 : 7))
					continue;
				characterEditIdentityKits[j] = k;
				break;
			}

		}

	}

	public void processGroundItems(int x, int y) {
		LinkedList linkedList = groundItems[plane][x][y];
		if (linkedList == null) {
			currentScene.clearGroundItem(plane, x, y);
			return;
		}
		int maxValue = 0xfa0a1f01;
		Object mostValuable = null;
		for (Item item = (Item) linkedList.first(); item != null; item = (Item) linkedList
				.next()) {
			ItemDefinition definition = ItemDefinition.lookup(item.itemId);
			int value = definition.value;
			if (definition.stackable)
				value *= item.itemCount + 1;
			if (value > maxValue) {
				maxValue = value;
				mostValuable = item;
			}
		}

		linkedList.addFirst(((Node) (mostValuable)));
		Object first = null;
		Object second = null;
		for (Item item = (Item) linkedList.first(); item != null; item = (Item) linkedList
				.next()) {
			if (item.itemId != ((Item) (mostValuable)).itemId && first == null)
				first = item;
			if (item.itemId != ((Item) (mostValuable)).itemId
					&& item.itemId != ((Item) (first)).itemId
					&& second == null)
				second = item;
		}

		int key = x + (y << 7) + 0x60000000;
		currentScene.method248(method110(y * 128 + 64, x * 128 + 64, (byte) 9, plane), plane,
				((Renderable) (mostValuable)), ((Renderable) (first)), key, ((Renderable) (second)), 2, y, x);
	}


	public void processGame() {
		if (systemUpdateTime > 1)
			systemUpdateTime--;
		if (anInt873 > 0)
			anInt873--;
		for (int i = 0; i < 5; i++)
			if (!parseIncomingPacket())
				break;

		if (!loggedIn)
			return;
		synchronized (mouseCapturer.objectLock) {
			if (accountFlagged) {
				if (super.clickType != 0 || mouseCapturer.coord >= 40) {
					outBuffer.putOpcode(171);
					outBuffer.putByte(0);
					int i2 = outBuffer.currentPosition;
					int i3 = 0;
					for (int i4 = 0; i4 < mouseCapturer.coord; i4++) {
						if (i2 - outBuffer.currentPosition >= 240)
							break;
						i3++;
						int k4 = mouseCapturer.coordsY[i4];
						if (k4 < 0)
							k4 = 0;
						else if (k4 > 502)
							k4 = 502;
						int j5 = mouseCapturer.coordsX[i4];
						if (j5 < 0)
							j5 = 0;
						else if (j5 > 764)
							j5 = 764;
						int l5 = k4 * 765 + j5;
						if (mouseCapturer.coordsY[i4] == -1 && mouseCapturer.coordsX[i4] == -1) {
							j5 = -1;
							k4 = -1;
							l5 = 0x7ffff;
						}
						if (j5 == anInt1011 && k4 == anInt1012) {
							if (duplicateClickCount < 2047)
								duplicateClickCount++;
						} else {
							int i6 = j5 - anInt1011;
							anInt1011 = j5;
							int j6 = k4 - anInt1012;
							anInt1012 = k4;
							if (duplicateClickCount < 8 && i6 >= -32 && i6 <= 31 && j6 >= -32 && j6 <= 31) {
								i6 += 32;
								j6 += 32;
								outBuffer.putShort((duplicateClickCount << 12) + (i6 << 6) + j6);
								duplicateClickCount = 0;
							} else if (duplicateClickCount < 8) {
								outBuffer.putTriByte(0x800000 + (duplicateClickCount << 19) + l5);
								duplicateClickCount = 0;
							} else {
								outBuffer.putInt(0xc0000000 + (duplicateClickCount << 19) + l5);
								duplicateClickCount = 0;
							}
						}
					}

					outBuffer.putLength(outBuffer.currentPosition - i2);
					if (i3 >= mouseCapturer.coord) {
						mouseCapturer.coord = 0;
					} else {
						mouseCapturer.coord -= i3;
						for (int l4 = 0; l4 < mouseCapturer.coord; l4++) {
							mouseCapturer.coordsX[l4] = mouseCapturer.coordsX[l4 + i3];
							mouseCapturer.coordsY[l4] = mouseCapturer.coordsY[l4 + i3];
						}

					}
				}
			} else {
				mouseCapturer.coord = 0;
			}
		}
		if (super.clickType != 0) {
			long l = (super.clickTime - aLong902) / 50L;
			if (l > 4095L)
				l = 4095L;
			aLong902 = super.clickTime;
			int j2 = super.clickY;
			if (j2 < 0)
				j2 = 0;
			else if (j2 > 502)
				j2 = 502;
			int j3 = super.clickX;
			if (j3 < 0)
				j3 = 0;
			else if (j3 > 764)
				j3 = 764;
			int j4 = j2 * 765 + j3;
			int i5 = 0;
			if (super.clickType == 2)
				i5 = 1;
			int k5 = (int) l;
			outBuffer.putOpcode(19);
			outBuffer.putInt((k5 << 20) + (i5 << 19) + j4);
		}
		if (anInt1264 > 0)
			anInt1264--;
		if (super.keyStatus[1] == 1 || super.keyStatus[2] == 1 || super.keyStatus[3] == 1
				|| super.keyStatus[4] == 1)
			aBoolean1265 = true;
		if (aBoolean1265 && anInt1264 <= 0) {
			anInt1264 = 20;
			aBoolean1265 = false;
			outBuffer.putOpcode(140);
			outBuffer.putLEShortDup(anInt1251);
			outBuffer.putLEShortDup(cameraHorizontal);
		}
		if (super.awtFocus && !aBoolean1275) {
			aBoolean1275 = true;
			outBuffer.putOpcode(187);
			outBuffer.putByte(1);
		}
		if (!super.awtFocus && aBoolean1275) {
			aBoolean1275 = false;
			outBuffer.putOpcode(187);
			outBuffer.putByte(0);
		}
		method143((byte) -40);
		method36(16220);
		method152();
		timeoutCounter++;
		if (timeoutCounter > 750)
			dropClient();
		method100(0);
		method67(-37214);
		processActorOverheadText();
		tickDelta++;
		if (crossType != 0) {
			crossIndex += 20;
			if (crossIndex >= 400)
				crossType = 0;
		}
		if (atInventoryInterfaceType != 0) {
			atInventoryLoopCycle++;
			if (atInventoryLoopCycle >= 15) {
				if (atInventoryInterfaceType == 2)
					redrawTabArea = true;
				if (atInventoryInterfaceType == 3)
					redrawChatbox = true;
				atInventoryInterfaceType = 0;
			}
		}
		if (activeInterfaceType != 0) {
			anInt1269++;
			if (super.mouseX > anInt1114 + 5 || super.mouseX < anInt1114 - 5 || super.mouseY > anInt1115 + 5
					|| super.mouseY < anInt1115 - 5)
				aBoolean1155 = true;
			if (super.mouseButtonPressed == 0) {
				if (activeInterfaceType == 2)
					redrawTabArea = true;
				if (activeInterfaceType == 3)
					redrawChatbox = true;
				activeInterfaceType = 0;
				if (aBoolean1155 && anInt1269 >= 5) {
					lastActiveInvInterface = -1;
					processRightClick(-521);
					if (lastActiveInvInterface == modifiedWidgetId && mouseInvInterfaceIndex != selectedInventorySlot) {
						Widget childInterface = Widget.forId(modifiedWidgetId);
						int i1 = 0;
						if (anInt955 == 1 && childInterface.contentType == 206)
							i1 = 1;
						if (childInterface.items[mouseInvInterfaceIndex] <= 0)
							i1 = 0;
						if (childInterface.itemDeletesDraged) {
							int k2 = selectedInventorySlot;
							int k3 = mouseInvInterfaceIndex;
							childInterface.items[k3] = childInterface.items[k2];
							childInterface.itemAmounts[k3] = childInterface.itemAmounts[k2];
							childInterface.items[k2] = -1;
							childInterface.itemAmounts[k2] = 0;
						} else if (i1 == 1) {
							int l2 = selectedInventorySlot;
							for (int l3 = mouseInvInterfaceIndex; l2 != l3;)
								if (l2 > l3) {
									childInterface.swapItems(l2, l2 - 1);
									l2--;
								} else if (l2 < l3) {
									childInterface.swapItems(l2, l2 + 1);
									l2++;
								}

						} else {
							childInterface.swapItems(selectedInventorySlot, mouseInvInterfaceIndex);
						}
						outBuffer.putOpcode(123);
						outBuffer.putLEShortAdded(mouseInvInterfaceIndex);
						outBuffer.putByteAdded(i1);
						outBuffer.putShortAdded(modifiedWidgetId);
						outBuffer.putLEShortDup(selectedInventorySlot);
					}
				} else if ((anInt1300 == 1 || menuHasAddFriend(menuActionRow - 1, aByte1161)) && menuActionRow > 2)
					determineMenuSize();
				else if (menuActionRow > 0)
					processMenuActions(menuActionRow - 1);
				atInventoryLoopCycle = 10;
				super.clickType = 0;
			}
		}
		if (Scene.clickedTileX != -1) {
			int dstX = Scene.clickedTileX;
			int dstY = Scene.anInt486;
			boolean flag = walk(true, false, dstY, localPlayer.pathY[0], 0, 0, 0, 0, dstX, 0, 0,
					localPlayer.pathX[0]);
			Scene.clickedTileX = -1;
			if (flag) {
				anInt1020 = super.clickX;
				anInt1021 = super.clickY;
				crossType = 1;
				crossIndex = 0;
			}
		}
		if (super.clickType == 1 && clickToContinueString != null) {
			clickToContinueString = null;
			redrawChatbox = true;
			super.clickType = 0;
		}
		processMenuClick();
		if (anInt1053 == -1) {
			method146((byte) 4);
			method21(false);
			method39(true);
		}
		if (super.mouseButtonPressed == 1 || super.clickType == 1)
			anInt1094++;
		if (anInt1284 != 0 || anInt1044 != 0 || anInt1129 != 0) {
			if (anInt893 < 100) {
				anInt893++;
				if (anInt893 == 100) {
					if (anInt1284 != 0)
						redrawChatbox = true;
					if (anInt1044 != 0)
						redrawTabArea = true;
				}
			}
		} else if (anInt893 > 0)
			anInt893--;
		if (loadingStage == 2)
			checkForGameUsages(409);
		if (loadingStage == 2 && oriented)
			calculateCameraPosition();
		for (int k = 0; k < 5; k++)
			quakeTimes[k]++;

		manageTextInputs();
		super.idleTime++;
		if (super.idleTime > 4500) {
			anInt873 = 250;
			super.idleTime -= 500;
			outBuffer.putOpcode(202);
		}
		anInt1118++;
		if (anInt1118 > 500) {
			anInt1118 = 0;
			int k1 = (int) (Math.random() * 8D);
			if ((k1 & 1) == 1)
				anInt853 += anInt854;
			if ((k1 & 2) == 2)
				anInt1009 += anInt1010;
			if ((k1 & 4) == 4)
				anInt1255 += anInt1256;
		}
		if (anInt853 < -50)
			anInt854 = 2;
		if (anInt853 > 50)
			anInt854 = -2;
		if (anInt1009 < -55)
			anInt1010 = 2;
		if (anInt1009 > 55)
			anInt1010 = -2;
		if (anInt1255 < -40)
			anInt1256 = 1;
		if (anInt1255 > 40)
			anInt1256 = -1;
		anInt1045++;
		if (anInt1045 > 500) {
			anInt1045 = 0;
			int l1 = (int) (Math.random() * 8D);
			if ((l1 & 1) == 1)
				anInt916 += anInt917;
			if ((l1 & 2) == 2)
				anInt1233 += anInt1234;
		}
		if (anInt916 < -60)
			anInt917 = 2;
		if (anInt916 > 60)
			anInt917 = -2;
		if (anInt1233 < -20)
			anInt1234 = 1;
		if (anInt1233 > 10)
			anInt1234 = -1;
		anInt872++;
		if (anInt872 > 50)
			outBuffer.putOpcode(40);
		try {
			if (gameConnection != null && outBuffer.currentPosition > 0) {
				gameConnection.write(outBuffer.currentPosition, 0, outBuffer.buffer);
				outBuffer.currentPosition = 0;
				anInt872 = 0;
				return;
			}
		} catch (IOException _ex) {
			dropClient();
			return;
		} catch (Exception exception) {
			logout();
		}
	}

	public void calculateCameraPosition() {
		int i = anInt874 * 128 + 64;
		int j = anInt875 * 128 + 64;
		int k = method110(j, i, (byte) 9, plane) - anInt876;
		if (cameraX < i) {
			cameraX += anInt877 + ((i - cameraX) * anInt878) / 1000;
			if (cameraX > i)
				cameraX = i;
		}
		if (cameraX > i) {
			cameraX -= anInt877 + ((cameraX - i) * anInt878) / 1000;
			if (cameraX < i)
				cameraX = i;
		}
		if (cameraZ < k) {
			cameraZ += anInt877 + ((k - cameraZ) * anInt878) / 1000;
			if (cameraZ > k)
				cameraZ = k;
		}
		if (cameraZ > k) {
			cameraZ -= anInt877 + ((cameraZ - k) * anInt878) / 1000;
			if (cameraZ < k)
				cameraZ = k;
		}
		if (cameraY < j) {
			cameraY += anInt877 + ((j - cameraY) * anInt878) / 1000;
			if (cameraY > j)
				cameraY = j;
		}
		if (cameraY > j) {
			cameraY -= anInt877 + ((cameraY - j) * anInt878) / 1000;
			if (cameraY < j)
				cameraY = j;
		}
		i = anInt993 * 128 + 64;
		j = anInt994 * 128 + 64;
		k = method110(j, i, (byte) 9, plane) - anInt995;
		int l = i - cameraX;
		int i1 = k - cameraZ;
		int j1 = j - cameraY;
		int k1 = (int) Math.sqrt(l * l + j1 * j1);
		int l1 = (int) (Math.atan2(i1, k1) * 325.94900000000001D) & 0x7ff;
		int j2 = (int) (Math.atan2(l, j1) * -325.94900000000001D) & 0x7ff;
		if (l1 < 128)
			l1 = 128;
		if (l1 > 383)
			l1 = 383;
		if (anInt1219 < l1) {
			anInt1219 += anInt996 + ((l1 - anInt1219) * anInt997) / 1000;
			if (anInt1219 > l1)
				anInt1219 = l1;
		}
		if (anInt1219 > l1) {
			anInt1219 -= anInt996 + ((anInt1219 - l1) * anInt997) / 1000;
			if (anInt1219 < l1)
				anInt1219 = l1;
		}
		int k2 = j2 - anInt1220;
		if (k2 > 1024)
			k2 -= 2048;
		if (k2 < -1024)
			k2 += 2048;
		if (k2 > 0) {
			anInt1220 += anInt996 + (k2 * anInt997) / 1000;
			anInt1220 &= 0x7ff;
		}
		if (k2 < 0) {
			anInt1220 -= anInt996 + (-k2 * anInt997) / 1000;
			anInt1220 &= 0x7ff;
		}
		int l2 = j2 - anInt1220;
		if (l2 > 1024)
			l2 -= 2048;
		if (l2 < -1024)
			l2 += 2048;
		if (l2 < 0 && k2 > 0 || l2 > 0 && k2 < 0)
			anInt1220 = j2;
	}

	public void manageTextInputs() {
		while (true) {
			int key = readCharacter();
			if (key == -1)
				break;
			if (openInterfaceId != -1 && openInterfaceId == reportAbuseInterfaceID) {
				if (key == 8 && reportedName.length() > 0)
					reportedName = reportedName.substring(0, reportedName.length() - 1);
				if ((key >= 97 && key <= 122 || key >= 65 && key <= 90 || key >= 48 && key <= 57 || key == 32)
						&& reportedName.length() < 12)
					reportedName += (char) key;
			} else if (messagePromptRaised) {
				if (key >= 32 && key <= 122 && chatMessage.length() < 80) {
					chatMessage += (char) key;
					redrawChatbox = true;
				}
				if (key == 8 && chatMessage.length() > 0) {
					chatMessage = chatMessage.substring(0, chatMessage.length() - 1);
					redrawChatbox = true;
				}
				if (key == 13 || key == 10) {
					messagePromptRaised = false;
					redrawChatbox = true;
					if (friendsListAction == 1) {
						long l = TextUtils.nameToLong(chatMessage);
						addFriend(l);
					}
					if (friendsListAction == 2 && friendsCount > 0) {
						long l1 = TextUtils.nameToLong(chatMessage);
						removeFriend(l1);
					}
					if (friendsListAction == 3 && chatMessage.length() > 0) {
						outBuffer.putOpcode(227);
						outBuffer.putByte(0);
						int j = outBuffer.currentPosition;
						outBuffer.putLong(aLong1141);
						ChatEncoder.put(chatMessage, outBuffer);
						outBuffer.putLength(outBuffer.currentPosition - j);
						chatMessage = ChatEncoder.formatChatMessage(chatMessage);
						//chatMessage = ChatCensor.censorString(chatMessage);
						addChatMessage(TextUtils.formatName(TextUtils.longToName(aLong1141)),
								chatMessage, 6);
						if (privateChatMode == 2) {
							privateChatMode = 1;
							aBoolean1212 = true;
							outBuffer.putOpcode(176);
							outBuffer.putByte(publicChatMode);
							outBuffer.putByte(privateChatMode);
							outBuffer.putByte(tradeMode);
						}
					}
					if (friendsListAction == 4 && ignoresCount < 100) {
						long l2 = TextUtils.nameToLong(chatMessage);
						addIgnore(anInt1154, l2);
					}
					if (friendsListAction == 5 && ignoresCount > 0) {
						long l3 = TextUtils.nameToLong(chatMessage);
						removeIgnore(325, l3);
					}
				}
			} else if (inputType == 1) {
				if (key >= 48 && key <= 57 && inputInputMessage.length() < 10) {
					inputInputMessage += (char) key;
					redrawChatbox = true;
				}
				if (key == 8 && inputInputMessage.length() > 0) {
					inputInputMessage = inputInputMessage.substring(0, inputInputMessage.length() - 1);
					redrawChatbox = true;
				}
				if (key == 13 || key == 10) {
					if (inputInputMessage.length() > 0) {
						int k = 0;
						try {
							k = Integer.parseInt(inputInputMessage);
						} catch (Exception _ex) {
						}
						outBuffer.putOpcode(75);
						outBuffer.putInt(k);
					}
					inputType = 0;
					redrawChatbox = true;
				}
			} else if (inputType == 2) {
				if (key >= 32 && key <= 122 && inputInputMessage.length() < 12) {
					inputInputMessage += (char) key;
					redrawChatbox = true;
				}
				if (key == 8 && inputInputMessage.length() > 0) {
					inputInputMessage = inputInputMessage.substring(0, inputInputMessage.length() - 1);
					redrawChatbox = true;
				}
				if (key == 13 || key == 10) {
					if (inputInputMessage.length() > 0) {
						outBuffer.putOpcode(206);
						outBuffer.putLong(TextUtils.nameToLong(inputInputMessage));
					}
					inputType = 0;
					redrawChatbox = true;
				}
			} else if (inputType == 3) {
				if (key >= 32 && key <= 122 && inputInputMessage.length() < 40) {
					inputInputMessage += (char) key;
					redrawChatbox = true;
				}
				if (key == 8 && inputInputMessage.length() > 0) {
					inputInputMessage = inputInputMessage.substring(0, inputInputMessage.length() - 1);
					redrawChatbox = true;
				}
			} else if (backDialogueId == -1 && anInt1053 == -1) {
				if (key >= 32 && key <= 122 && chatboxInput.length() < 80) {
					chatboxInput += (char) key;
					redrawChatbox = true;
				}
				if (key == 8 && chatboxInput.length() > 0) {
					chatboxInput = chatboxInput.substring(0, chatboxInput.length() - 1);
					redrawChatbox = true;
				}
				if ((key == 13 || key == 10) && chatboxInput.length() > 0) {
					if (playerRights == 2) {
						if (chatboxInput.equals("::clientdrop"))
							dropClient();
						if (chatboxInput.equals("::lag"))
							method138();
						if (chatboxInput.equals("::prefetchmusic")) {
							for (int i_417_ = 0; i_417_ < onDemandRequester.fileCount(2); i_417_++)
								onDemandRequester.setPriority((byte) 1, 2, i_417_);

						}
						if (chatboxInput.equals("::fpson"))
							fps = true;
						if (chatboxInput.equals("::fpsoff"))
							fps = false;
						if (chatboxInput.equals("::noclip")) {
							for (int j1 = 0; j1 < 4; j1++) {
								for (int k1 = 1; k1 < 103; k1++) {
									for (int j2 = 1; j2 < 103; j2++)
										currentCollisionMap[j1].adjacency[k1][j2] = 0;

								}

							}

						}
					}
					if (chatboxInput.startsWith("::")) {
						outBuffer.putOpcode(56);
						outBuffer.putByte(chatboxInput.length() - 1);
						outBuffer.putString(chatboxInput.substring(2));
					} else {
						String s = chatboxInput.toLowerCase();
						int colourCode = 0;
						if (s.startsWith("yellow:")) {
							colourCode = 0;
							chatboxInput = chatboxInput.substring(7);
						} else if (s.startsWith("red:")) {
							colourCode = 1;
							chatboxInput = chatboxInput.substring(4);
						} else if (s.startsWith("green:")) {
							colourCode = 2;
							chatboxInput = chatboxInput.substring(6);
						} else if (s.startsWith("cyan:")) {
							colourCode = 3;
							chatboxInput = chatboxInput.substring(5);
						} else if (s.startsWith("purple:")) {
							colourCode = 4;
							chatboxInput = chatboxInput.substring(7);
						} else if (s.startsWith("white:")) {
							colourCode = 5;
							chatboxInput = chatboxInput.substring(6);
						} else if (s.startsWith("flash1:")) {
							colourCode = 6;
							chatboxInput = chatboxInput.substring(7);
						} else if (s.startsWith("flash2:")) {
							colourCode = 7;
							chatboxInput = chatboxInput.substring(7);
						} else if (s.startsWith("flash3:")) {
							colourCode = 8;
							chatboxInput = chatboxInput.substring(7);
						} else if (s.startsWith("glow1:")) {
							colourCode = 9;
							chatboxInput = chatboxInput.substring(6);
						} else if (s.startsWith("glow2:")) {
							colourCode = 10;
							chatboxInput = chatboxInput.substring(6);
						} else if (s.startsWith("glow3:")) {
							colourCode = 11;
							chatboxInput = chatboxInput.substring(6);
						}
						s = chatboxInput.toLowerCase();
						int effectCode = 0;
						if (s.startsWith("wave:")) {
							effectCode = 1;
							chatboxInput = chatboxInput.substring(5);
						} else if (s.startsWith("wave2:")) {
							effectCode = 2;
							chatboxInput = chatboxInput.substring(6);
						} else if (s.startsWith("shake:")) {
							effectCode = 3;
							chatboxInput = chatboxInput.substring(6);
						} else if (s.startsWith("scroll:")) {
							effectCode = 4;
							chatboxInput = chatboxInput.substring(7);
						} else if (s.startsWith("slide:")) {
							effectCode = 5;
							chatboxInput = chatboxInput.substring(6);
						}
						outBuffer.putOpcode(49);
						outBuffer.putByte(0);
						int bufPos = outBuffer.currentPosition;
						outBuffer.putByteNegated(colourCode);
						outBuffer.putByteAdded(effectCode);
						chatBuffer.currentPosition = 0;
						ChatEncoder.put(chatboxInput, chatBuffer);
						outBuffer.putBytes(chatBuffer.buffer, 0,
								chatBuffer.currentPosition);
						outBuffer.putLength(outBuffer.currentPosition - bufPos);
						chatboxInput = ChatEncoder.formatChatMessage(chatboxInput);
						chatboxInput = ChatCensor.censorString(chatboxInput);
						localPlayer.forcedChat = chatboxInput;
						localPlayer.textColour = colourCode;
						localPlayer.textEffect = effectCode;
						localPlayer.textCycle = 150;
						if (playerRights == 2)
							addChatMessage("@cr2@" + localPlayer.playerName,
									localPlayer.forcedChat, 2);
						else if (playerRights == 1)
							addChatMessage("@cr1@" + localPlayer.playerName,
									localPlayer.forcedChat, 2);
						else
							addChatMessage(localPlayer.playerName, localPlayer.forcedChat, 2);
						if (publicChatMode == 2) {
							publicChatMode = 3;
							aBoolean1212 = true;
							outBuffer.putOpcode(176);
							outBuffer.putByte(publicChatMode);
							outBuffer.putByte(privateChatMode);
							outBuffer.putByte(tradeMode);
						}
					}
					chatboxInput = "";
					redrawChatbox = true;
				}
			}
		}
	}

	private DataInputStream openJaggrabStream(String request) throws IOException {
		if (!useJaggrab) {
            if (SignLink.applet != null)
                return SignLink.openURL(request);
            else
                return new DataInputStream((new URL(getCodeBase(), request)).openStream());
        }

		if (jaggrabSocket != null) {
			try {
				jaggrabSocket.close();
			} catch (Exception ignored) {}

			jaggrabSocket = null;
		}

		byte[] buffer = String.format("JAGGRAB /%s\n\n", request).getBytes();
		jaggrabSocket = openSocket(Configuration.JAGGRAB_PORT);

		jaggrabSocket.setSoTimeout(10000);
		jaggrabSocket.getOutputStream().write(buffer);

        return new DataInputStream(jaggrabSocket.getInputStream());
	}

	public Socket openSocket(int port) throws IOException {
		if (SignLink.applet != null)
			return SignLink.openSocket(port);

		return new Socket(InetAddress.getByName(getCodeBase().getHost()), port);
	}

	public boolean parseIncomingPacket() {
		if (gameConnection == null)
			return false;
		try {
			int available = gameConnection.getAvailable();
			if (available == 0)
				return false;
			if (opcode == -1) {
				gameConnection.read(buffer.buffer, 0, 1);
				opcode = buffer.buffer[0] & 0xff;
				if (incomingRandom != null)
					opcode = opcode - incomingRandom.nextInt() & 0xff;
				packetSize = PacketConstants.PACKET_SIZES[opcode];
				available--;
			}
			if (packetSize == -1)
				if (available > 0) {
					gameConnection.read(buffer.buffer, 0, 1);
					packetSize = buffer.buffer[0] & 0xff;
					available--;
				} else {
					return false;
				}
			if (packetSize == -2)
				if (available > 1) {
					gameConnection.read(buffer.buffer, 0, 2);
					buffer.currentPosition = 0;
					packetSize = buffer.getUnsignedLEShort();
					available -= 2;
				} else {
					return false;
				}
			if (available < packetSize)
				return false;
			buffer.currentPosition = 0;
			gameConnection.read(buffer.buffer, 0, packetSize);
			timeoutCounter = 0;
			thirdLastOpcode = secondLastOpcode;
			secondLastOpcode = lastOpcode;
			lastOpcode = opcode;
			if (opcode == 166) {
				int l = buffer.method552();
				int l10 = buffer.method552();
				int interfaceId = buffer.getUnsignedLEShort();
				Widget class13_5 = Widget.forId(interfaceId);
				class13_5.anInt228 = l10;
				class13_5.anInt259 = l;
				opcode = -1;
				return true;
			}
			if (opcode == 186) {
				int i1 = buffer.method550();
				int interfaceId = buffer.getLittleShortA();
				int l16 = buffer.method550();
				int i22 = buffer.method549();
				Widget.forId(interfaceId).rotationX = i1;
				Widget.forId(interfaceId).rotationY = i22;
				Widget.forId(interfaceId).zoom = l16;
				opcode = -1;
				return true;
			}
			if (opcode == 216) {
				int j1 = buffer.getLittleShortA();
				int interfaceId = buffer.getLittleShortA();
				Widget.forId(interfaceId).modelType = 1;
				Widget.forId(interfaceId).modelId = j1;
				opcode = -1;
				return true;
			}
			if (opcode == 26) {
				int k1 = buffer.getUnsignedLEShort();
				int k11 = buffer.getUnsignedByte();
				int i17 = buffer.getUnsignedLEShort();
				if (i17 == 65535) {
					if (currentSound < 50) {
						sound[currentSound] = (short) k1;
						soundType[currentSound] = k11;
						soundDelay[currentSound] = 0;
						currentSound++;
					}
				} else if (aBoolean1301 && !lowMemory && currentSound < 50) {
					sound[currentSound] = k1;
					soundType[currentSound] = k11;
					soundDelay[currentSound] = i17 + SoundTrack.trackDelays[k1];
					currentSound++;
				}
				opcode = -1;
				return true;
			}
			if (opcode == 182) {
				int l1 = buffer.method550();
				byte byte0 = buffer.getSignedByteSubtracted();
				anIntArray1005[l1] = byte0;
				if (widgetSettings[l1] != byte0) {
					widgetSettings[l1] = byte0;
					updateVarp(0, l1);
					redrawTabArea = true;
					if (dialogueId != -1)
						redrawChatbox = true;
				}
				opcode = -1;
				return true;
			}
			if (opcode == 13) {
				for (int i2 = 0; i2 < players.length; i2++)
					if (players[i2] != null)
						players[i2].emoteAnimation = -1;

				for (int l11 = 0; l11 < npcs.length; l11++)
					if (npcs[l11] != null)
						npcs[l11].emoteAnimation = -1;

				opcode = -1;
				return true;
			}
			if (opcode == 156) {
				minimapState = buffer.getUnsignedByte();
				opcode = -1;
				return true;
			}
			if (opcode == 162) {
				int j2 = buffer.method550();
				int interfaceId = buffer.method549();
				Widget.forId(interfaceId).modelType = 2;
				Widget.forId(interfaceId).modelId = j2;
				opcode = -1;
				return true;
			}
			if (opcode == 109) {
				int k2 = buffer.getUnsignedLEShort();
				method112((byte) 36, k2);
				if (anInt1089 != -1) {
					method44(aBoolean1190, anInt1089);
					anInt1089 = -1;
					redrawTabArea = true;
					aBoolean950 = true;
				}
				if (anInt1053 != -1) {
					method44(aBoolean1190, anInt1053);
					anInt1053 = -1;
					aBoolean1046 = true;
				}
				if (anInt960 != -1) {
					method44(aBoolean1190, anInt960);
					anInt960 = -1;
				}
				if (openInterfaceId != -1) {
					method44(aBoolean1190, openInterfaceId);
					openInterfaceId = -1;
				}
				if (backDialogueId != k2) {
					method44(aBoolean1190, backDialogueId);
					backDialogueId = k2;
				}
				aBoolean1239 = false;
				redrawChatbox = true;
				opcode = -1;
				return true;
			}
			if (opcode == 220) {
				int songID = buffer.getLittleShortA();
				if (songID == 65535)
					songID = -1;
				if (songID != currentSong && musicEnabled && !lowMemory && previousSong == 0) {
					nextSong = songID;
					songChanging = true;
					onDemandRequester.request(2, nextSong);
				}
				currentSong = songID;
				opcode = -1;
				return true;
			}
			if (opcode == 249) {
				int fileId = buffer.method549();
				int j12 = buffer.method554();
				if (musicEnabled && !lowMemory) {
					nextSong = fileId;
					songChanging = false;
					onDemandRequester.request(2, nextSong); // request something from cache!?!
					previousSong = j12;
				}
				opcode = -1;
				return true;
			}
			if (opcode == 158) {
				int j3 = buffer.method552();
				if (j3 != dialogueId) {
					method44(aBoolean1190, dialogueId);
					dialogueId = j3;
				}
				redrawChatbox = true;
				opcode = -1;
				return true;
			}
			if (opcode == 218) { // set interface colour(?)
				int interfaceId = buffer.getUnsignedLEShort();
				int rgb = buffer.method550();
				int j17 = rgb >> 10 & 0x1f;
				int j22 = rgb >> 5 & 0x1f;
				int l24 = rgb & 0x1f;
				Widget.forId(interfaceId).disabledColor = (j17 << 19) + (j22 << 11) + (l24 << 3);
				opcode = -1;
				return true;
			}
			if (opcode == 157) { // update player option
				int slot = buffer.getByteNegated();
				String option = buffer.getString();
				int alwaysOnTop = buffer.getUnsignedByte();
				if (slot >= 1 && slot <= 5) {
					if (option.equalsIgnoreCase("null"))
						option = null;
					aStringArray1069[slot - 1] = option;
					aBooleanArray1070[slot - 1] = alwaysOnTop == 0;
				}
				opcode = -1;
				return true;
			}
			if (opcode == 6) {
				messagePromptRaised = false;
				inputType = 2;
				inputInputMessage = "";
				redrawChatbox = true;
				opcode = -1;
				return true;
			}
			if (opcode == 201) {
				publicChatMode = buffer.getUnsignedByte();
				privateChatMode = buffer.getUnsignedByte();
				tradeMode = buffer.getUnsignedByte();
				aBoolean1212 = true;
				redrawChatbox = true;
				opcode = -1;
				return true;
			}
			if (opcode == 199) {
				anInt1197 = buffer.getUnsignedByte();
				if (anInt1197 == 1)
					anInt1226 = buffer.getUnsignedLEShort();
				if (anInt1197 >= 2 && anInt1197 <= 6) {
					if (anInt1197 == 2) {
						anInt847 = 64;
						anInt848 = 64;
					}
					if (anInt1197 == 3) {
						anInt847 = 0;
						anInt848 = 64;
					}
					if (anInt1197 == 4) {
						anInt847 = 128;
						anInt848 = 64;
					}
					if (anInt1197 == 5) {
						anInt847 = 64;
						anInt848 = 0;
					}
					if (anInt1197 == 6) {
						anInt847 = 64;
						anInt848 = 128;
					}
					anInt1197 = 2;
					anInt844 = buffer.getUnsignedLEShort();
					anInt845 = buffer.getUnsignedLEShort();
					anInt846 = buffer.getUnsignedByte();
				}
				if (anInt1197 == 10)
					anInt1151 = buffer.getUnsignedLEShort();
				opcode = -1;
				return true;
			}
			if (opcode == 167) {
				oriented = true;
				anInt993 = buffer.getUnsignedByte();
				anInt994 = buffer.getUnsignedByte();
				anInt995 = buffer.getUnsignedLEShort();
				anInt996 = buffer.getUnsignedByte();
				anInt997 = buffer.getUnsignedByte();
				if (anInt997 >= 100) {
					int i4 = anInt993 * 128 + 64;
					int l12 = anInt994 * 128 + 64;
					int l17 = method110(l12, i4, (byte) 9, plane) - anInt995;
					int k22 = i4 - cameraX;
					int i25 = l17 - cameraZ;
					int k27 = l12 - cameraY;
					int i30 = (int) Math.sqrt(k22 * k22 + k27 * k27);
					anInt1219 = (int) (Math.atan2(i25, i30) * 325.94900000000001D) & 0x7ff;
					anInt1220 = (int) (Math.atan2(k22, k27) * -325.94900000000001D) & 0x7ff;
					if (anInt1219 < 128)
						anInt1219 = 128;
					if (anInt1219 > 383)
						anInt1219 = 383;
				}
				opcode = -1;
				return true;
			}
			if (opcode == 5) {
				logout(); // simulate a crash??
				opcode = -1;
				return false;
			}
			if (opcode == 115) {
				int j4 = buffer.method557();
				int i13 = buffer.method549();
				anIntArray1005[i13] = j4;
				if (widgetSettings[i13] != j4) {
					widgetSettings[i13] = j4;
					updateVarp(0, i13);
					redrawTabArea = true;
					if (dialogueId != -1)
						redrawChatbox = true;
				}
				opcode = -1;
				return true;
			}
			if (opcode == 29) { // close open interfaces??
				if (anInt1089 != -1) {
					method44(aBoolean1190, anInt1089);
					anInt1089 = -1;
					redrawTabArea = true;
					aBoolean950 = true;
				}
				if (backDialogueId != -1) {
					method44(aBoolean1190, backDialogueId);
					backDialogueId = -1;
					redrawChatbox = true;
				}
				if (anInt1053 != -1) {
					method44(aBoolean1190, anInt1053);
					anInt1053 = -1;
					aBoolean1046 = true;
				}
				if (anInt960 != -1) {
					method44(aBoolean1190, anInt960);
					anInt960 = -1;
				}
				if (openInterfaceId != -1) {
					method44(aBoolean1190, openInterfaceId);
					openInterfaceId = -1;
				}
				if (inputType != 0) {
					inputType = 0;
					redrawChatbox = true;
				}
				aBoolean1239 = false;
				opcode = -1;
				return true;
			}
			if (opcode == 76) { // open welcome screen
				anInt1083 = buffer.method549();
				anInt1075 = buffer.getLittleShortA();
				buffer.getUnsignedLEShort();
				anInt1208 = buffer.getUnsignedLEShort();
				anInt1170 = buffer.method549();
				anInt1273 = buffer.method550();
				anInt1215 = buffer.method550();
				anInt992 = buffer.getUnsignedLEShort();
				lastAddress = buffer.method555();
				anInt1034 = buffer.getLittleShortA();
				buffer.getByteAdded();
				SignLink.dnsLookup(TextUtils.decodeAddress(lastAddress));
				opcode = -1;
				return true;
			}
			if (opcode == 63) { // server message
				String message = buffer.getString();
				if (message.endsWith(":tradereq:")) {
					String s3 = message.substring(0, message.indexOf(":"));
					long l18 = TextUtils.nameToLong(s3);
					boolean flag1 = false;
					for (int l27 = 0; l27 < ignoresCount; l27++) {
						if (ignores[l27] != l18)
							continue;
						flag1 = true;
						break;
					}

					if (!flag1 && !inTutorialIsland)
						addChatMessage(s3, "wishes to trade with you.", 4);
				} else if (message.endsWith(":duelreq:")) {
					String s4 = message.substring(0, message.indexOf(":"));
					long l19 = TextUtils.nameToLong(s4);
					boolean flag2 = false;
					for (int i28 = 0; i28 < ignoresCount; i28++) {
						if (ignores[i28] != l19)
							continue;
						flag2 = true;
						break;
					}

					if (!flag2 && !inTutorialIsland)
						addChatMessage(s4, "wishes to duel with you.", 8);
				} else if (message.endsWith(":chalreq:")) {
					String s5 = message.substring(0, message.indexOf(":"));
					long l20 = TextUtils.nameToLong(s5);
					boolean flag3 = false;
					for (int j28 = 0; j28 < ignoresCount; j28++) {
						if (ignores[j28] != l20)
							continue;
						flag3 = true;
						break;
					}

					if (!flag3 && !inTutorialIsland) {
						String s8 = message.substring(message.indexOf(":") + 1, message.length() - 9);
						addChatMessage(s5, s8, 8);
					}
				} else {
					addChatMessage("", message, 0);
				}
				opcode = -1;
				return true;
			}
			if (opcode == 50) {
				int k4 = buffer.getSignedShort();
				if (k4 >= 0)
					method112((byte) 36, k4);
				if (k4 != anInt1279) {
					method44(aBoolean1190, anInt1279);
					anInt1279 = k4;
				}
				opcode = -1;
				return true;
			}
			if (opcode == 82) { // make interface (in)visible maybe?
				boolean flag = buffer.getUnsignedByte() == 1;
				int interfaceId = buffer.getUnsignedLEShort();
				Widget.forId(interfaceId).hiddenUntilHovered = flag;
				opcode = -1;
				return true;
			}
			if (opcode == 174) {
				if (anInt1285 == 12)
					redrawTabArea = true;
				anInt1030 = buffer.getSignedShort();
				opcode = -1;
				return true;
			}
			if (opcode == 233) {
				anInt1319 = buffer.getUnsignedByte();
				opcode = -1;
				return true;
			}
			if (opcode == 61) {
				destinationX = 0;
				opcode = -1;
				return true;
			}
			if (opcode == 128) {
				int l4 = buffer.method550();
				int k13 = buffer.getLittleShortA();
				if (backDialogueId != -1) {
					method44(aBoolean1190, backDialogueId);
					backDialogueId = -1;
					redrawChatbox = true;
				}
				if (anInt1053 != -1) {
					method44(aBoolean1190, anInt1053);
					anInt1053 = -1;
					aBoolean1046 = true;
				}
				if (anInt960 != -1) {
					method44(aBoolean1190, anInt960);
					anInt960 = -1;
				}
				if (openInterfaceId != l4) {
					method44(aBoolean1190, openInterfaceId);
					openInterfaceId = l4;
				}
				if (anInt1089 != k13) {
					method44(aBoolean1190, anInt1089);
					anInt1089 = k13;
				}
				if (inputType != 0) {
					inputType = 0;
					redrawChatbox = true;
				}
				redrawTabArea = true;
				aBoolean950 = true;
				aBoolean1239 = false;
				opcode = -1;
				return true;
			}
			if (opcode == 67) {
				int i5 = buffer.getUnsignedByte();
				int l13 = buffer.getUnsignedByte();
				int i18 = buffer.getUnsignedByte();
				int l22 = buffer.getUnsignedByte();
				aBooleanArray927[i5] = true;
				anIntArray1105[i5] = l13;
				anIntArray852[i5] = i18;
				anIntArray991[i5] = l22;
				quakeTimes[i5] = 0;
				opcode = -1;
				return true;
			}
			if (opcode == 134) { // set items in interface
				redrawTabArea = true;
				int interfaceId = buffer.getUnsignedLEShort();
				Widget inter = Widget.forId(interfaceId);
				while (buffer.currentPosition < packetSize) {
					int slot = buffer.getSmart();
					int id = buffer.getUnsignedLEShort();
					int amount = buffer.getUnsignedByte();
					if (amount == 255)
						amount = buffer.getInt();
					if (slot >= 0 && slot < inter.items.length) {
						inter.items[slot] = id;
						inter.itemAmounts[slot] = amount;
					}
				}
				opcode = -1;
				return true;
			}
			if (opcode == 78) { // update friend status
				long friend = buffer.getLong();
				int nodeId = buffer.getUnsignedByte();
				String s7 = TextUtils.formatName(TextUtils.longToName(friend));
				for (int k25 = 0; k25 < friendsCount; k25++) {
					if (friend != friends[k25])
						continue;
					if (friendWorlds[k25] != nodeId) {
						friendWorlds[k25] = nodeId;
						redrawTabArea = true;
						if (nodeId > 0)
							addChatMessage("", s7 + " has logged in.", 5);
						if (nodeId == 0)
							addChatMessage("", s7 + " has logged out.", 5);
					}
					s7 = null;
					break;
				}

				if (s7 != null && friendsCount < 200) {
					friends[friendsCount] = friend;
					friendUsernames[friendsCount] = s7;
					friendWorlds[friendsCount] = nodeId;
					friendsCount++;
					redrawTabArea = true;
				}
				for (boolean flag5 = false; !flag5;) {
					flag5 = true;
					for (int j30 = 0; j30 < friendsCount - 1; j30++)
						if (friendWorlds[j30] != world && friendWorlds[j30 + 1] == world
								|| friendWorlds[j30] == 0 && friendWorlds[j30 + 1] != 0) {
							int l31 = friendWorlds[j30];
							friendWorlds[j30] = friendWorlds[j30 + 1];
							friendWorlds[j30 + 1] = l31;
							String s10 = friendUsernames[j30];
							friendUsernames[j30] = friendUsernames[j30 + 1];
							friendUsernames[j30 + 1] = s10;
							long l33 = friends[j30];
							friends[j30] = friends[j30 + 1];
							friends[j30 + 1] = l33;
							redrawTabArea = true;
							flag5 = false;
						}

				}

				opcode = -1;
				return true;
			}
			if (opcode == 58) { // enter amount interface
				messagePromptRaised = false;
				inputType = 1;
				inputInputMessage = "";
				redrawChatbox = true;
				opcode = -1;
				return true;
			}
			if (opcode == 252) {
				anInt1285 = buffer.getByteNegated();
				redrawTabArea = true;
				aBoolean950 = true;
				opcode = -1;
				return true;
			}
			if (opcode == 40) {
				placementY = buffer.getByteSubtracted();
				placementX = buffer.getByteNegated();
				for (int k5 = placementX; k5 < placementX + 8; k5++) {
					for (int i14 = placementY; i14 < placementY + 8; i14++)
						if (groundItems[plane][k5][i14] != null) {
							groundItems[plane][k5][i14] = null;
							processGroundItems(k5, i14);
						}

				}

				for (SpawnObjectNode spawnObjectNode = (SpawnObjectNode) aClass6_1261.first(); spawnObjectNode != null; spawnObjectNode = (SpawnObjectNode) aClass6_1261
						.next())
					if (spawnObjectNode.anInt1393 >= placementX && spawnObjectNode.anInt1393 < placementX + 8
							&& spawnObjectNode.anInt1394 >= placementY && spawnObjectNode.anInt1394 < placementY + 8
							&& spawnObjectNode.anInt1391 == plane)
						spawnObjectNode.anInt1390 = 0;

				opcode = -1;
				return true;
			}
			if (opcode == 255) { // show player in an interface *maybe*?
				int interfaceId = buffer.getLittleShortA();
				Widget.forId(interfaceId).modelType = 3;
				if (localPlayer.npcDefinition == null) // maybe that is the appear as npc thing?
					Widget.forId(interfaceId).modelId = (localPlayer.appearanceColors[0] << 25) + (localPlayer.appearanceColors[4] << 20)
							+ (localPlayer.appearance[0] << 15) + (localPlayer.appearance[8] << 10)
							+ (localPlayer.appearance[11] << 5) + localPlayer.appearance[1];
				else
					Widget.forId(interfaceId).modelId = (int) (0x12345678L + localPlayer.npcDefinition.id);
				opcode = -1;
				return true;
			}
			if (opcode == 135) { // private message (?)
				long l6 = buffer.getLong();
				int i19 = buffer.getInt();
				int j23 = buffer.getUnsignedByte();
				boolean flag4 = false;
				for (int k28 = 0; k28 < 100; k28++) {
					if (anIntArray1258[k28] != i19)
						continue;
					flag4 = true;
					break;
				}

				if (j23 <= 1) {
					for (int k30 = 0; k30 < ignoresCount; k30++) {
						if (ignores[k30] != l6)
							continue;
						flag4 = true;
						break;
					}

				}
				if (!flag4 && !inTutorialIsland)
					try {
						anIntArray1258[anInt1152] = i19;
						anInt1152 = (anInt1152 + 1) % 100;
						String s9 = ChatEncoder.get(packetSize - 13, buffer);
						if (j23 != 3)
							s9 = ChatCensor.censorString(s9);
						if (j23 == 2 || j23 == 3)
							addChatMessage("@cr2@" + TextUtils.formatName(TextUtils.longToName(l6)),
									s9, 7);
						else if (j23 == 1)
							addChatMessage("@cr1@" + TextUtils.formatName(TextUtils.longToName(l6)),
									s9, 7);
						else
							addChatMessage(TextUtils.formatName(TextUtils.longToName(l6)), s9, 3);
					} catch (Exception exception1) {
						SignLink.reportError("cde1");
					}
				opcode = -1;
				return true;
			}
			if (opcode == 183) {
				placementX = buffer.getUnsignedByte();
				placementY = buffer.getByteAdded();
				while (buffer.currentPosition < packetSize) {
					int j6 = buffer.getUnsignedByte();
					parsePlacementPacket(buffer, j6);
				}
				opcode = -1;
				return true;
			}
			if (opcode == 159) { // open interface
				int interfaceId = buffer.getLittleShortA();
				method112((byte) 36, interfaceId);
				if (anInt1089 != -1) {
					method44(aBoolean1190, anInt1089);
					anInt1089 = -1;
					redrawTabArea = true;
					aBoolean950 = true;
				}
				if (backDialogueId != -1) {
					method44(aBoolean1190, backDialogueId);
					backDialogueId = -1;
					redrawChatbox = true;
				}
				if (anInt1053 != -1) {
					method44(aBoolean1190, anInt1053);
					anInt1053 = -1;
					aBoolean1046 = true;
				}
				if (anInt960 != -1) {
					method44(aBoolean1190, anInt960);
					anInt960 = -1;
				}
				if (openInterfaceId != interfaceId) {
					method44(aBoolean1190, openInterfaceId);
					openInterfaceId = interfaceId;
				}
				if (inputType != 0) {
					inputType = 0;
					redrawChatbox = true;
				}
				aBoolean1239 = false;
				opcode = -1;
				return true;
			}
			if (opcode == 246) {
				int i7 = buffer.getLittleShortA();
				method112((byte) 36, i7);
				if (backDialogueId != -1) {
					method44(aBoolean1190, backDialogueId);
					backDialogueId = -1;
					redrawChatbox = true;
				}
				if (anInt1053 != -1) {
					method44(aBoolean1190, anInt1053);
					anInt1053 = -1;
					aBoolean1046 = true;
				}
				if (anInt960 != -1) {
					method44(aBoolean1190, anInt960);
					anInt960 = -1;
				}
				if (openInterfaceId != -1) {
					method44(aBoolean1190, openInterfaceId);
					openInterfaceId = -1;
				}
				if (anInt1089 != i7) {
					method44(aBoolean1190, anInt1089);
					anInt1089 = i7;
				}
				if (inputType != 0) {
					inputType = 0;
					redrawChatbox = true;
				}
				redrawTabArea = true;
				aBoolean950 = true;
				aBoolean1239 = false;
				opcode = -1;
				return true;
			}
			if (opcode == 49) {
				redrawTabArea = true;
				int j7 = buffer.getByteNegated();
				int j14 = buffer.getUnsignedByte();
				int j19 = buffer.getInt();
				anIntArray843[j7] = j19;
				anIntArray1029[j7] = j14;
				anIntArray1054[j7] = 1;
				for (int k23 = 0; k23 < 98; k23++)
					if (j19 >= SKILL_EXPERIENCE[k23])
						anIntArray1054[j7] = k23 + 2;

				opcode = -1;
				return true;
			}
			if (opcode == 206) { // update all items in interface
				redrawTabArea = true;
				int interfaceId = buffer.getUnsignedLEShort();
				Widget inter = Widget.forId(interfaceId);
				int items = buffer.getUnsignedLEShort();
				for (int item = 0; item < items; item++) {
					inter.items[item] = buffer.getLittleShortA();
					int amount = buffer.getByteNegated();
					if (amount == 255)
						amount = buffer.method555();
					inter.itemAmounts[item] = amount;
				}

				for (int i26 = items; i26 < inter.items.length; i26++) {
					inter.items[i26] = 0;
					inter.itemAmounts[i26] = 0;
				}

				opcode = -1;
				return true;
			}
			if (opcode == 222 || opcode == 53) { // new map region
				int tmpChunkX = chunkX;
				int tmpChunkY = chunkY;
				if (opcode == 222) {
					tmpChunkY = buffer.getUnsignedLEShort();
					tmpChunkX = buffer.getLittleShortA();
					aBoolean1163 = false;
				}
				if (opcode == 53) {
					tmpChunkX = buffer.method550();
					buffer.initBitAccess();
					for (int z = 0; z < 4; z++) {
						for (int x = 0; x < 13; x++) {
							for (int y = 0; y < 13; y++) {
								int flag = buffer.getBits(1);
								if (flag == 1)
									constructedMapPalette[z][x][y] = buffer.getBits(26);
								else
									constructedMapPalette[z][x][y] = -1;
							}

						}

					}

					buffer.finishBitAccess();
					tmpChunkY = buffer.method550();
					aBoolean1163 = true;
				}
				if (chunkX == tmpChunkX && chunkY == tmpChunkY && loadingStage == 2) {
					opcode = -1;
					return true;
				}
				chunkX = tmpChunkX;
				chunkY = tmpChunkY;
				nextTopLeftTileX = (chunkX - 6) * 8;
				nextTopRightTileY = (chunkY - 6) * 8;
				aBoolean1067 = false;
				if ((chunkX / 8 == 48 || chunkX / 8 == 49) && chunkY / 8 == 48)
					aBoolean1067 = true;
				if (chunkX / 8 == 48 && chunkY / 8 == 148)
					aBoolean1067 = true;
				loadingStage = 1;
				aLong1229 = System.currentTimeMillis();
				method125(null, "Loading - please wait.");
				if (opcode == 222) {
					int count = 0;
					for (int fileX = (chunkX - 6) / 8; fileX <= (chunkX + 6) / 8; fileX++) {
						for (int fileY = (chunkY - 6) / 8; fileY <= (chunkY + 6) / 8; fileY++)
							count++;

					}

					aByteArrayArray838 = new byte[count][];
					aByteArrayArray1232 = new byte[count][];
					coordinates = new int[count];
					anIntArray857 = new int[count];
					anIntArray858 = new int[count];
					count = 0;
					for (int fileX = (chunkX - 6) / 8; fileX <= (chunkX + 6) / 8; fileX++) {
						for (int fileY = (chunkY - 6) / 8; fileY <= (chunkY + 6) / 8; fileY++) {
							coordinates[count] = (fileX << 8) + fileY;
							if (aBoolean1067
									&& (fileY == 49 || fileY == 149 || fileY == 147 || fileX == 50 || fileX == 49 && fileY == 47)) {
								anIntArray857[count] = -1;
								anIntArray858[count] = -1;
								count++;
							} else {
								int l30 = anIntArray857[count] = onDemandRequester.regId(0, fileX, fileY, 0);
								if (l30 != -1)
									onDemandRequester.request(3, l30);
								int i32 = anIntArray858[count] = onDemandRequester.regId(0, fileX, fileY, 1);
								if (i32 != -1)
									onDemandRequester.request(3, i32);
								count++;
							}
						}

					}

				}
				if (opcode == 53) {
					int uniqueCount = 0;
					int fileIndices[] = new int[676];
					for (int tileZ = 0; tileZ < 4; tileZ++) {
						for (int tileX = 0; tileX < 13; tileX++) {
							for (int tileY = 0; tileY < 13; tileY++) {
								int data = constructedMapPalette[tileZ][tileX][tileY];
								if (data != -1) {
									int chunkX = data >> 14 & 0x3ff;
									int chunkY = data >> 3 & 0x7ff;
									int fileIndex = (chunkX / 8 << 8) + chunkY / 8;
									for (int pos = 0; pos < uniqueCount; pos++) {
										if (fileIndices[pos] != fileIndex)
											continue;
										fileIndex = -1;
										break;
									}

									if (fileIndex != -1)
										fileIndices[uniqueCount++] = fileIndex;
								}
							}

						}

					}

					aByteArrayArray838 = new byte[uniqueCount][];
					aByteArrayArray1232 = new byte[uniqueCount][];
					coordinates = new int[uniqueCount];
					anIntArray857 = new int[uniqueCount];
					anIntArray858 = new int[uniqueCount];
					for (int pos = 0; pos < uniqueCount; pos++) {
						int j31 = coordinates[pos] = fileIndices[pos];
						int fileX = j31 >> 8 & 0xff;
						int fileY = j31 & 0xff;
						int i34 = anIntArray857[pos] = onDemandRequester.regId(0, fileX, fileY, 0);
						if (i34 != -1)
							onDemandRequester.request(3, i34);
						int k34 = anIntArray858[pos] = onDemandRequester.regId(0, fileX, fileY, 1);
						if (k34 != -1)
							onDemandRequester.request(3, k34);
					}

				}
				int deltaX = nextTopLeftTileX - topLeftTileX;
				int deltaY = nextTopRightTileY - topLeftTileY;
				topLeftTileX = nextTopLeftTileX;
				topLeftTileY = nextTopRightTileY;
				for (int id = 0; id < 16384; id++) {
					Npc npc = npcs[id];
					if (npc != null) {
						for (int pos = 0; pos < 10; pos++) {
							((Actor) (npc)).pathX[pos] -= deltaX;
							((Actor) (npc)).pathY[pos] -= deltaY;
						}

						npc.worldX -= deltaX * 128;
						npc.worldY -= deltaY * 128;
					}
				}

				for (int id = 0; id < anInt968; id++) {
					Player player = players[id];
					if (player != null) {
						for (int pos = 0; pos < 10; pos++) {
							((Actor) (player)).pathX[pos] -= deltaX;
							((Actor) (player)).pathY[pos] -= deltaY;
						}

						player.worldX -= deltaX * 128;
						player.worldY -= deltaY * 128;
					}
				}

				aBoolean1209 = true;
				byte byte1 = 0;
				byte byte2 = 104;
				byte byte3 = 1;
				if (deltaX < 0) {
					byte1 = 103;
					byte2 = -1;
					byte3 = -1;
				}
				byte byte4 = 0;
				byte byte5 = 104;
				byte byte6 = 1;
				if (deltaY < 0) {
					byte4 = 103;
					byte5 = -1;
					byte6 = -1;
				}
				for (int i35 = byte1; i35 != byte2; i35 += byte3) {
					for (int j35 = byte4; j35 != byte5; j35 += byte6) {
						int k35 = i35 + deltaX;
						int l35 = j35 + deltaY;
						for (int i36 = 0; i36 < 4; i36++)
							if (k35 >= 0 && l35 >= 0 && k35 < 104 && l35 < 104)
								groundItems[i36][i35][j35] = groundItems[i36][k35][l35];
							else
								groundItems[i36][i35][j35] = null;

					}

				}

				for (SpawnObjectNode spawnObjectNode_1 = (SpawnObjectNode) aClass6_1261.first(); spawnObjectNode_1 != null; spawnObjectNode_1 = (SpawnObjectNode) aClass6_1261
						.next()) {
					spawnObjectNode_1.anInt1393 -= deltaX;
					spawnObjectNode_1.anInt1394 -= deltaY;
					if (spawnObjectNode_1.anInt1393 < 0 || spawnObjectNode_1.anInt1394 < 0 || spawnObjectNode_1.anInt1393 >= 104
							|| spawnObjectNode_1.anInt1394 >= 104)
						spawnObjectNode_1.remove();
				}

				if (destinationX != 0) {
					destinationX -= deltaX;
					destinationY -= deltaY;
				}
				oriented = false;
				opcode = -1;
				return true;
			}
			if (opcode == 190) {
				systemUpdateTime = buffer.method549() * 30;
				opcode = -1;
				return true;
			}
			if (opcode == 41 || opcode == 121 || opcode == 203 || opcode == 106 || opcode == 59 || opcode == 181
					|| opcode == 208 || opcode == 107 || opcode == 142 || opcode == 88 || opcode == 152) {
				parsePlacementPacket(buffer, opcode); // these are to do with objects iirc
				opcode = -1;
				return true;
			}
			if (opcode == 125) {
				if (anInt1285 == 12)
					redrawTabArea = true;
				anInt1324 = buffer.getUnsignedByte();
				opcode = -1;
				return true;
			}
			if (opcode == 21) { // show a model on an interface??
				int scale = buffer.getUnsignedLEShort();
				int itemId = buffer.method549();
				int interfaceId = buffer.getLittleShortA();
				if (itemId == 65535) {
					Widget.forId(interfaceId).modelType = 0;
					opcode = -1;
					return true;
				} else {
					ItemDefinition class16 = ItemDefinition.lookup(itemId);
					Widget.forId(interfaceId).modelType = 4;
					Widget.forId(interfaceId).modelId = itemId;
					Widget.forId(interfaceId).rotationX = class16.modelRotationX;
					Widget.forId(interfaceId).rotationY = class16.modelRotationY;
					Widget.forId(interfaceId).zoom = (class16.modelScale * 100) / scale;
					opcode = -1;
					return true;
				}
			}
			if (opcode == 3) {
				oriented = true;
				anInt874 = buffer.getUnsignedByte();
				anInt875 = buffer.getUnsignedByte();
				anInt876 = buffer.getUnsignedLEShort();
				anInt877 = buffer.getUnsignedByte();
				anInt878 = buffer.getUnsignedByte();
				if (anInt878 >= 100) {
					cameraX = anInt874 * 128 + 64;
					cameraY = anInt875 * 128 + 64;
					cameraZ = method110(cameraY, cameraX, (byte) 9, plane) - anInt876;
				}
				opcode = -1;
				return true;
			}
			if (opcode == 2) {
				int interfaceId = buffer.getLittleShortA();
				int i15 = buffer.method553();
				Widget class13_3 = Widget.forId(interfaceId);
				if (class13_3.disabledAnimation != i15 || i15 == -1) {
					class13_3.disabledAnimation = i15;
					class13_3.anInt235 = 0;
					class13_3.anInt227 = 0;
				}
				opcode = -1;
				return true;
			}
			if (opcode == 71) {
				method48(buffer, aBoolean1038, packetSize);
				opcode = -1;
				return true;
			}
			if (opcode == 226) { // ignore list
				ignoresCount = packetSize / 8;
				for (int k8 = 0; k8 < ignoresCount; k8++)
					ignores[k8] = buffer.getLong();

				opcode = -1;
				return true;
			}
			if (opcode == 10) {
				int l8 = buffer.getByteSubtracted();
				int j15 = buffer.method550();
				if (j15 == 65535)
					j15 = -1;
				if (anIntArray1081[l8] != j15) {
					method44(aBoolean1190, anIntArray1081[l8]);
					anIntArray1081[l8] = j15;
				}
				redrawTabArea = true;
				aBoolean950 = true;
				opcode = -1;
				return true;
			}
			if (opcode == 219) { // reset all items on interface?
				int interfaceId = buffer.method549();
				Widget class13_2 = Widget.forId(interfaceId);
				for (int k21 = 0; k21 < class13_2.items.length; k21++) {
					class13_2.items[k21] = -1;
					class13_2.items[k21] = 0;
				}

				opcode = -1;
				return true;
			}
			if (opcode == 238) {
				anInt1213 = buffer.getUnsignedByte();
				if (anInt1213 == anInt1285) {
					if (anInt1213 == 3)
						anInt1285 = 1;
					else
						anInt1285 = 3;
					redrawTabArea = true;
				}
				opcode = -1;
				return true;
			}
			if (opcode == 148) {
				oriented = false;
				for (int j9 = 0; j9 < 5; j9++)
					aBooleanArray927[j9] = false;

				opcode = -1;
				return true;
			}
			if (opcode == 126) {
				playerMembers = buffer.getUnsignedByte();
				thisPlayerServerId = buffer.method549();
				opcode = -1;
				return true;
			}
			if (opcode == 75) {
				placementX = buffer.getByteNegated();
				placementY = buffer.getByteAdded();
				opcode = -1;
				return true;
			}
			if (opcode == 253) { // open fullscreen interface
				int k9 = buffer.method549();
				int k15 = buffer.method550();
				method112((byte) 36, k15);
				if (k9 != -1)
					method112((byte) 36, k9);
				if (openInterfaceId != -1) {
					method44(aBoolean1190, openInterfaceId);
					openInterfaceId = -1;
				}
				if (anInt1089 != -1) {
					method44(aBoolean1190, anInt1089);
					anInt1089 = -1;
				}
				if (backDialogueId != -1) {
					method44(aBoolean1190, backDialogueId);
					backDialogueId = -1;
				}
				if (anInt1053 != k15) {
					method44(aBoolean1190, anInt1053);
					anInt1053 = k15;
				}
				if (anInt960 != k15) {
					method44(aBoolean1190, anInt960);
					anInt960 = k9;
				}
				inputType = 0;
				aBoolean1239 = false;
				opcode = -1;
				return true;
			}
			if (opcode == 251) {
				friendListStatus = buffer.getUnsignedByte();
				redrawTabArea = true;
				opcode = -1;
				return true;
			}
			if (opcode == 18) {
				int l9 = buffer.getUnsignedLEShort();
				int interfaceId = buffer.method550();
				int l21 = buffer.method549();
				Widget.forId(interfaceId).anInt218 = (l9 << 16) + l21;
				opcode = -1;
				return true;
			}
			if (opcode == 90) { // player update
				updatePlayers(packetSize, buffer);
				aBoolean1209 = false;
				opcode = -1;
				return true;
			}
			if (opcode == 113) {
				for (int i10 = 0; i10 < widgetSettings.length; i10++)
					if (widgetSettings[i10] != anIntArray1005[i10]) {
						widgetSettings[i10] = anIntArray1005[i10];
						updateVarp(0, i10);
						redrawTabArea = true;
					}

				opcode = -1;
				return true;
			}
			if (opcode == 232) { // update interface string?
				int j10 = buffer.getLittleShortA();
				String s6 = buffer.getString();
				Widget.forId(j10).disabledText = s6;
				if (Widget.forId(j10).parentId == anIntArray1081[anInt1285])
					redrawTabArea = true;
				opcode = -1;
				return true;
			}
			if (opcode == 200) {
				int interfaceId = buffer.getUnsignedLEShort();
				int i16 = buffer.getLittleShortA();
				Widget class13_4 = Widget.forId(interfaceId);
				if (class13_4 != null && class13_4.type == 0) {
					if (i16 < 0)
						i16 = 0;
					if (i16 > class13_4.scrollLimit - class13_4.height)
						i16 = class13_4.scrollLimit - class13_4.height;
					class13_4.anInt231 = i16;
				}
				opcode = -1;
				return true;
			}
			SignLink.reportError("T1 - " + opcode + "," + packetSize + " - " + secondLastOpcode + "," + thirdLastOpcode);
			logout();
		} catch (IOException _ex) {
			dropClient();
		} catch (Exception exception) {
			String s1 = "T2 - " + opcode + "," + secondLastOpcode + "," + thirdLastOpcode + " - " + packetSize + ","
					+ (nextTopLeftTileX + localPlayer.pathX[0]) + ","
					+ (nextTopRightTileY + localPlayer.pathY[0]) + " - ";
			for (int j16 = 0; j16 < packetSize && j16 < 50; j16++)
				s1 = s1 + buffer.buffer[j16] + ",";

			SignLink.reportError(s1);
			logout();

			exception.printStackTrace();
		}
		return true;
	}

	private void drawMenuTooltip() {
		if (menuActionRow < 2 && itemSelected == 0 && widgetSelected == 0)
			return;

		String str;

		if (itemSelected == 1 && menuActionRow < 2)
			str = "Use " + aString1150 + " with...";
		else if (widgetSelected == 1 && menuActionRow < 2)
			str = selectedWidgetName + "...";
		else
			str = menuActionTexts[menuActionRow - 1];

		if (menuActionRow > 2)
			str = str + "@whi@ / " + (menuActionRow - 2) + " more options";

		fontBold.drawShadowedSeededAlphaString(str, 4, 15, 0xffffff, pulseCycle / 1000);
	}

	public boolean walk(boolean flag, boolean flag1, int dstY, int srcY, int k, int l, int packetType, int j1, int dstX, int l1,
			int i2, int srcX) {
		byte byte0 = 104;
		byte byte1 = 104;
		for (int x = 0; x < byte0; x++) {
			for (int y = 0; y < byte1; y++) {
				anIntArrayArray885[x][y] = 0;
				cost[x][y] = 0x5f5e0ff;
			}

		}

		int curX = srcX;
		int curY = srcY;
		anIntArrayArray885[srcX][srcY] = 99;
		cost[srcX][srcY] = 0;
		int k3 = 0;
		int l3 = 0;
		anIntArray1123[k3] = srcX;
		anIntArray1124[k3++] = srcY;
		boolean flag2 = false;
		int i4 = anIntArray1123.length;
		int masks[][] = currentCollisionMap[plane].adjacency;
		while (l3 != k3) {
			curX = anIntArray1123[l3];
			curY = anIntArray1124[l3];
			l3 = (l3 + 1) % i4;
			if (curX == dstX && curY == dstY) {
				flag2 = true;
				break;
			}
			if (j1 != 0) {
				if ((j1 < 5 || j1 == 10) && currentCollisionMap[plane].reachedWall(curX, curY, dstX, dstY, j1 - 1, i2)) {
					flag2 = true;
					break;
				}
				if (j1 < 10 && currentCollisionMap[plane].reachedWallDecoration(curX, curY, dstX, dstY, j1 - 1, i2)) {
					flag2 = true;
					break;
				}
			}
			if (k != 0 && l != 0 && currentCollisionMap[plane].reachedFacingObject(curX, curY, dstX, dstY, k, l, l1)) {
				flag2 = true;
				break;
			}
			int nextCost = cost[curX][curY] + 1;
			if (curX > 0 && anIntArrayArray885[curX - 1][curY] == 0 && (masks[curX - 1][curY] & 0x1280108) == 0) {
				anIntArray1123[k3] = curX - 1;
				anIntArray1124[k3] = curY;
				k3 = (k3 + 1) % i4;
				anIntArrayArray885[curX - 1][curY] = 2;
				cost[curX - 1][curY] = nextCost;
			}
			if (curX < byte0 - 1 && anIntArrayArray885[curX + 1][curY] == 0 && (masks[curX + 1][curY] & 0x1280180) == 0) {
				anIntArray1123[k3] = curX + 1;
				anIntArray1124[k3] = curY;
				k3 = (k3 + 1) % i4;
				anIntArrayArray885[curX + 1][curY] = 8;
				cost[curX + 1][curY] = nextCost;
			}
			if (curY > 0 && anIntArrayArray885[curX][curY - 1] == 0 && (masks[curX][curY - 1] & 0x1280102) == 0) {
				anIntArray1123[k3] = curX;
				anIntArray1124[k3] = curY - 1;
				k3 = (k3 + 1) % i4;
				anIntArrayArray885[curX][curY - 1] = 1;
				cost[curX][curY - 1] = nextCost;
			}
			if (curY < byte1 - 1 && anIntArrayArray885[curX][curY + 1] == 0 && (masks[curX][curY + 1] & 0x1280120) == 0) {
				anIntArray1123[k3] = curX;
				anIntArray1124[k3] = curY + 1;
				k3 = (k3 + 1) % i4;
				anIntArrayArray885[curX][curY + 1] = 4;
				cost[curX][curY + 1] = nextCost;
			}
			if (curX > 0 && curY > 0 && anIntArrayArray885[curX - 1][curY - 1] == 0 && (masks[curX - 1][curY - 1] & 0x128010e) == 0
					&& (masks[curX - 1][curY] & 0x1280108) == 0 && (masks[curX][curY - 1] & 0x1280102) == 0) {
				anIntArray1123[k3] = curX - 1;
				anIntArray1124[k3] = curY - 1;
				k3 = (k3 + 1) % i4;
				anIntArrayArray885[curX - 1][curY - 1] = 3;
				cost[curX - 1][curY - 1] = nextCost;
			}
			if (curX < byte0 - 1 && curY > 0 && anIntArrayArray885[curX + 1][curY - 1] == 0
					&& (masks[curX + 1][curY - 1] & 0x1280183) == 0 && (masks[curX + 1][curY] & 0x1280180) == 0
					&& (masks[curX][curY - 1] & 0x1280102) == 0) {
				anIntArray1123[k3] = curX + 1;
				anIntArray1124[k3] = curY - 1;
				k3 = (k3 + 1) % i4;
				anIntArrayArray885[curX + 1][curY - 1] = 9;
				cost[curX + 1][curY - 1] = nextCost;
			}
			if (curX > 0 && curY < byte1 - 1 && anIntArrayArray885[curX - 1][curY + 1] == 0
					&& (masks[curX - 1][curY + 1] & 0x1280138) == 0 && (masks[curX - 1][curY] & 0x1280108) == 0
					&& (masks[curX][curY + 1] & 0x1280120) == 0) {
				anIntArray1123[k3] = curX - 1;
				anIntArray1124[k3] = curY + 1;
				k3 = (k3 + 1) % i4;
				anIntArrayArray885[curX - 1][curY + 1] = 6;
				cost[curX - 1][curY + 1] = nextCost;
			}
			if (curX < byte0 - 1 && curY < byte1 - 1 && anIntArrayArray885[curX + 1][curY + 1] == 0
					&& (masks[curX + 1][curY + 1] & 0x12801e0) == 0 && (masks[curX + 1][curY] & 0x1280180) == 0
					&& (masks[curX][curY + 1] & 0x1280120) == 0) {
				anIntArray1123[k3] = curX + 1;
				anIntArray1124[k3] = curY + 1;
				k3 = (k3 + 1) % i4;
				anIntArrayArray885[curX + 1][curY + 1] = 12;
				cost[curX + 1][curY + 1] = nextCost;
			}
		}
		anInt1126 = 0;
		if (!flag2)
			if (flag) {
				int l4 = 1000;
				int j5 = 100;
				byte byte2 = 10;
				for (int i6 = dstX - byte2; i6 <= dstX + byte2; i6++) {
					for (int k6 = dstY - byte2; k6 <= dstY + byte2; k6++)
						if (i6 >= 0 && k6 >= 0 && i6 < 104 && k6 < 104 && cost[i6][k6] < 100) {
							int i7 = 0;
							if (i6 < dstX)
								i7 = dstX - i6;
							else if (i6 > (dstX + k) - 1)
								i7 = i6 - ((dstX + k) - 1);
							int j7 = 0;
							if (k6 < dstY)
								j7 = dstY - k6;
							else if (k6 > (dstY + l) - 1)
								j7 = k6 - ((dstY + l) - 1);
							int k7 = i7 * i7 + j7 * j7;
							if (k7 < l4 || k7 == l4 && cost[i6][k6] < j5) {
								l4 = k7;
								j5 = cost[i6][k6];
								curX = i6;
								curY = k6;
							}
						}

				}

				if (l4 == 1000)
					return false;
				if (curX == srcX && curY == srcY)
					return false;
				anInt1126 = 1;
			} else {
				return false;
			}
		l3 = 0;
		if (flag1)
			startup();
		anIntArray1123[l3] = curX;
		anIntArray1124[l3++] = curY;
		int k5;
		for (int i5 = k5 = anIntArrayArray885[curX][curY]; curX != srcX || curY != srcY; i5 = anIntArrayArray885[curX][curY]) {
			if (i5 != k5) {
				k5 = i5;
				anIntArray1123[l3] = curX;
				anIntArray1124[l3++] = curY;
			}
			if ((i5 & 2) != 0)
				curX++;
			else if ((i5 & 8) != 0)
				curX--;
			if ((i5 & 1) != 0)
				curY++;
			else if ((i5 & 4) != 0)
				curY--;
		}

		if (l3 > 0) {
			int j4 = l3;
			if (j4 > 25)
				j4 = 25;
			l3--;
			int l5 = anIntArray1123[l3];
			int j6 = anIntArray1124[l3];
			if (packetType == 0) {
				outBuffer.putOpcode(28);
				outBuffer.putByte(j4 + j4 + 3);
			}
			if (packetType == 1) {
				outBuffer.putOpcode(213);
				outBuffer.putByte(j4 + j4 + 3 + 14);
			}
			if (packetType == 2) {
				outBuffer.putOpcode(247);
				outBuffer.putByte(j4 + j4 + 3);
			}
			outBuffer.putLEShortAdded(l5 + nextTopLeftTileX);
			outBuffer.putByte(super.keyStatus[5] != 1 ? 0 : 1);
			outBuffer.putLEShortAdded(j6 + nextTopRightTileY);
			destinationX = anIntArray1123[0];
			destinationY = anIntArray1124[0];
			for (int l6 = 1; l6 < j4; l6++) {
				l3--;
				outBuffer.putByte(anIntArray1123[l3] - l5);
				outBuffer.putByteSubtracted(anIntArray1124[l3] - j6);
			}

			return true;
		}
		return packetType != 1;
	}

	public void method36(int i) {
		if (i != 16220)
			anInt1328 = 458;
		if (loadingStage == 2) {
			for (SpawnObjectNode spawnObjectNode = (SpawnObjectNode) aClass6_1261.first(); spawnObjectNode != null; spawnObjectNode = (SpawnObjectNode) aClass6_1261
					.next()) {
				if (spawnObjectNode.anInt1390 > 0)
					spawnObjectNode.anInt1390--;
				if (spawnObjectNode.anInt1390 == 0) {
					if (spawnObjectNode.anInt1387 < 0
							|| Region.method170(spawnObjectNode.anInt1389, aByte1143, spawnObjectNode.anInt1387)) {
						method45(spawnObjectNode.anInt1388, spawnObjectNode.anInt1393, spawnObjectNode.anInt1387,
								spawnObjectNode.anInt1394, spawnObjectNode.anInt1391, spawnObjectNode.anInt1389, (byte) 1,
								spawnObjectNode.anInt1392);
						spawnObjectNode.remove();
					}
				} else {
					if (spawnObjectNode.anInt1395 > 0)
						spawnObjectNode.anInt1395--;
					if (spawnObjectNode.anInt1395 == 0
							&& spawnObjectNode.anInt1393 >= 1
							&& spawnObjectNode.anInt1394 >= 1
							&& spawnObjectNode.anInt1393 <= 102
							&& spawnObjectNode.anInt1394 <= 102
							&& (spawnObjectNode.anInt1384 < 0 || Region.method170(spawnObjectNode.anInt1386, aByte1143,
									spawnObjectNode.anInt1384))) {
						method45(spawnObjectNode.anInt1385, spawnObjectNode.anInt1393, spawnObjectNode.anInt1384,
								spawnObjectNode.anInt1394, spawnObjectNode.anInt1391, spawnObjectNode.anInt1386, (byte) 1,
								spawnObjectNode.anInt1392);
						spawnObjectNode.anInt1395 = -1;
						if (spawnObjectNode.anInt1384 == spawnObjectNode.anInt1387 && spawnObjectNode.anInt1387 == -1)
							spawnObjectNode.remove();
						else if (spawnObjectNode.anInt1384 == spawnObjectNode.anInt1387
								&& spawnObjectNode.anInt1385 == spawnObjectNode.anInt1388
								&& spawnObjectNode.anInt1386 == spawnObjectNode.anInt1389)
							spawnObjectNode.remove();
					}
				}
			}

		}
	}

	public String method37(int i) {
		if (i != -42588)
			opcode = buffer.getUnsignedByte();
		if (SignLink.applet != null)
			return SignLink.applet.getDocumentBase().getHost().toLowerCase();
		if (super.gameFrame != null)
			return "runescape.com";
		else
			return super.getDocumentBase().getHost().toLowerCase();
	}

	public void method38(int i, int j, int k, Player class50_sub1_sub4_sub3_sub2, int l) {
		if (class50_sub1_sub4_sub3_sub2 == localPlayer)
			return;
		if (menuActionRow >= 400)
			return;
		if (l != 0)
			aBoolean963 = !aBoolean963;
		String s;
		if (class50_sub1_sub4_sub3_sub2.anInt1759 == 0)
			s = class50_sub1_sub4_sub3_sub2.playerName
					+ getCombatLevelColour(localPlayer.combatLevel, class50_sub1_sub4_sub3_sub2.combatLevel) + " (level-"
					+ class50_sub1_sub4_sub3_sub2.combatLevel + ")";
		else
			s = class50_sub1_sub4_sub3_sub2.playerName + " (skill-" + class50_sub1_sub4_sub3_sub2.anInt1759 + ")";
		if (itemSelected == 1) {
			menuActionTexts[menuActionRow] = "Use " + aString1150 + " with @whi@" + s;
			menuActionTypes[menuActionRow] = 596;
			selectedMenuActions[menuActionRow] = i;
			firstMenuOperand[menuActionRow] = k;
			secondMenuOperand[menuActionRow] = j;
			menuActionRow++;
		} else if (widgetSelected == 1) {
			if ((anInt1173 & 8) == 8) {
				menuActionTexts[menuActionRow] = selectedWidgetName + " @whi@" + s;
				menuActionTypes[menuActionRow] = 918;
				selectedMenuActions[menuActionRow] = i;
				firstMenuOperand[menuActionRow] = k;
				secondMenuOperand[menuActionRow] = j;
				menuActionRow++;
			}
		} else {
			for (int i1 = 4; i1 >= 0; i1--)
				if (aStringArray1069[i1] != null) {
					menuActionTexts[menuActionRow] = aStringArray1069[i1] + " @whi@" + s;
					char c = '\0';
					if (aStringArray1069[i1].equalsIgnoreCase("attack")) {
						if (class50_sub1_sub4_sub3_sub2.combatLevel > localPlayer.combatLevel)
							c = '\u07D0';
						if (localPlayer.teamId != 0 && class50_sub1_sub4_sub3_sub2.teamId != 0)
							if (localPlayer.teamId == class50_sub1_sub4_sub3_sub2.teamId)
								c = '\u07D0';
							else
								c = '\0';
					} else if (aBooleanArray1070[i1])
						c = '\u07D0';
					if (i1 == 0)
						menuActionTypes[menuActionRow] = 200 + c;
					if (i1 == 1)
						menuActionTypes[menuActionRow] = 493 + c;
					if (i1 == 2)
						menuActionTypes[menuActionRow] = 408 + c;
					if (i1 == 3)
						menuActionTypes[menuActionRow] = 677 + c;
					if (i1 == 4)
						menuActionTypes[menuActionRow] = 876 + c;
					selectedMenuActions[menuActionRow] = i;
					firstMenuOperand[menuActionRow] = k;
					secondMenuOperand[menuActionRow] = j;
					menuActionRow++;
				}

		}
		for (int j1 = 0; j1 < menuActionRow; j1++)
			if (menuActionTypes[j1] == 14) {
				menuActionTexts[j1] = "Walk here @whi@" + s;
				return;
			}

	}

	public void method39(boolean flag) {
		if (!flag)
			groundItems = null;
		if (super.clickType == 1) {
			if (super.clickX >= 6 && super.clickX <= 106 && super.clickY >= 467 && super.clickY <= 499) {
				publicChatMode = (publicChatMode + 1) % 4;
				aBoolean1212 = true;
				redrawChatbox = true;
				outBuffer.putOpcode(176);
				outBuffer.putByte(publicChatMode);
				outBuffer.putByte(privateChatMode);
				outBuffer.putByte(tradeMode);
			}
			if (super.clickX >= 135 && super.clickX <= 235 && super.clickY >= 467 && super.clickY <= 499) {
				privateChatMode = (privateChatMode + 1) % 3;
				aBoolean1212 = true;
				redrawChatbox = true;
				outBuffer.putOpcode(176);
				outBuffer.putByte(publicChatMode);
				outBuffer.putByte(privateChatMode);
				outBuffer.putByte(tradeMode);
			}
			if (super.clickX >= 273 && super.clickX <= 373 && super.clickY >= 467 && super.clickY <= 499) {
				tradeMode = (tradeMode + 1) % 3;
				aBoolean1212 = true;
				redrawChatbox = true;
				outBuffer.putOpcode(176);
				outBuffer.putByte(publicChatMode);
				outBuffer.putByte(privateChatMode);
				outBuffer.putByte(tradeMode);
			}
			if (super.clickX >= 412 && super.clickX <= 512 && super.clickY >= 467 && super.clickY <= 499)
				if (openInterfaceId == -1) {
					closeWidgets();
					reportedName = "";
					reportMutePlayer = false;
					reportAbuseInterfaceID = openInterfaceId = Widget.anInt246;
				} else {
					addChatMessage("", "Please close the interface you have open before using 'report abuse'", 0);
				}
			anInt1160++;
			if (anInt1160 > 161) {
				anInt1160 = 0;
				outBuffer.putOpcode(22);
				outBuffer.putShort(38304);
			}
		}
	}

	private void parsePlayerBlocks(Buffer buffer) {
		for (int i = 0; i < updatedPlayerCount; i++) {
			int id = updatedPlayers[i];
			Player player = players[id];
			int mask = buffer.getUnsignedByte();

			if ((mask & 0x20) != 0)
				mask += buffer.getUnsignedByte() << 8;

			parsePlayerBlock(id, player, mask, buffer);
		}
	}

	private void updateLocalPlayerMovement(Buffer buffer) {
		buffer.initBitAccess();

		int moved = buffer.getBits(1);

		if (moved == 0)
			return;

		int moveType = buffer.getBits(2);

		if (moveType == 0) {
			updatedPlayers[updatedPlayerCount++] = thisPlayerId;
			return;
		}

		if (moveType == 1) {
			int direction = buffer.getBits(3);

			localPlayer.move(direction, false);

			int blockUpdateRequired = buffer.getBits(1);

			if (blockUpdateRequired == 1)
				updatedPlayers[updatedPlayerCount++] = thisPlayerId;
			return;
		}

		if (moveType == 2) {
			int direction1 = buffer.getBits(3);

			localPlayer.move(direction1, true);

			int direction2 = buffer.getBits(3);

			localPlayer.move(direction2, true);

			int blockUpdateRequired = buffer.getBits(1);

			if (blockUpdateRequired == 1)
				updatedPlayers[updatedPlayerCount++] = thisPlayerId;
			return;
		}

		if (moveType == 3) {
			int discardWalkingQueue = buffer.getBits(1);
			plane = buffer.getBits(2);
			int localY = buffer.getBits(7);
			int localX = buffer.getBits(7);
			int blockUpdateRequired = buffer.getBits(1);

			if (blockUpdateRequired == 1)
				updatedPlayers[updatedPlayerCount++] = thisPlayerId;

			localPlayer.setPosition(localX, localY, discardWalkingQueue == 1);
		}
	}

	public void method42(int i, int j, Widget class13, byte byte0, int k, int l, int i1, int j1, int k1) {
		if (aBoolean1127)
			anInt1303 = 32;
		else
			anInt1303 = 0;
		aBoolean1127 = false;
		if (byte0 != 102) {
			for (int l1 = 1; l1 > 0; l1++);
		}
		if (i1 >= k1 && i1 < k1 + 16 && k >= j && k < j + 16) {
			class13.anInt231 -= anInt1094 * 4;
			if (l == 1)
				redrawTabArea = true;
			if (l == 2 || l == 3)
				redrawChatbox = true;
			return;
		}
		if (i1 >= k1 && i1 < k1 + 16 && k >= (j + j1) - 16 && k < j + j1) {
			class13.anInt231 += anInt1094 * 4;
			if (l == 1)
				redrawTabArea = true;
			if (l == 2 || l == 3)
				redrawChatbox = true;
			return;
		}
		if (i1 >= k1 - anInt1303 && i1 < k1 + 16 + anInt1303 && k >= j + 16 && k < (j + j1) - 16 && anInt1094 > 0) {
			int i2 = ((j1 - 32) * j1) / i;
			if (i2 < 8)
				i2 = 8;
			int j2 = k - j - 16 - i2 / 2;
			int k2 = j1 - 32 - i2;
			class13.anInt231 = ((i - j1) * j2) / k2;
			if (l == 1)
				redrawTabArea = true;
			if (l == 2 || l == 3)
				redrawChatbox = true;
			aBoolean1127 = true;
		}
	}

	public void method43(byte byte0) {
		if (itemSelected == 0 && widgetSelected == 0) {
			menuActionTexts[menuActionRow] = "Walk here";
			menuActionTypes[menuActionRow] = 14;
			firstMenuOperand[menuActionRow] = super.mouseX;
			secondMenuOperand[menuActionRow] = super.mouseY;
			menuActionRow++;
		}
		int i = -1;
		if (byte0 != 7)
			opcode = -1;
		for (int j = 0; j < Model.anInt1708; j++) {
			int k = Model.anIntArray1709[j];
			int l = k & 0x7f;
			int i1 = k >> 7 & 0x7f;
			int j1 = k >> 29 & 3;
			int k1 = k >> 14 & 0x7fff;
			if (k == i)
				continue;
			i = k;
			if (j1 == 2 && currentScene.method271(plane, l, i1, k) >= 0) {
				GameObjectDefinition class47 = GameObjectDefinition.getDefinition(k1);
				if (class47.childrenIds != null)
					class47 = class47.getChildDefinition();
				if (class47 == null)
					continue;
				if (itemSelected == 1) {
					menuActionTexts[menuActionRow] = "Use " + aString1150 + " with @cya@" + class47.name;
					menuActionTypes[menuActionRow] = 467;
					selectedMenuActions[menuActionRow] = k;
					firstMenuOperand[menuActionRow] = l;
					secondMenuOperand[menuActionRow] = i1;
					menuActionRow++;
				} else if (widgetSelected == 1) {
					if ((anInt1173 & 4) == 4) {
						menuActionTexts[menuActionRow] = selectedWidgetName + " @cya@" + class47.name;
						menuActionTypes[menuActionRow] = 376;
						selectedMenuActions[menuActionRow] = k;
						firstMenuOperand[menuActionRow] = l;
						secondMenuOperand[menuActionRow] = i1;
						menuActionRow++;
					}
				} else {
					if (class47.options != null) {
						for (int l1 = 4; l1 >= 0; l1--)
							if (class47.options[l1] != null) {
								menuActionTexts[menuActionRow] = class47.options[l1] + " @cya@"
										+ class47.name;
								if (l1 == 0)
									menuActionTypes[menuActionRow] = 35;
								if (l1 == 1)
									menuActionTypes[menuActionRow] = 389;
								if (l1 == 2)
									menuActionTypes[menuActionRow] = 888;
								if (l1 == 3)
									menuActionTypes[menuActionRow] = 892;
								if (l1 == 4)
									menuActionTypes[menuActionRow] = 1280;
								selectedMenuActions[menuActionRow] = k;
								firstMenuOperand[menuActionRow] = l;
								secondMenuOperand[menuActionRow] = i1;
								menuActionRow++;
							}

					}
					menuActionTexts[menuActionRow] = "Examine @cya@" + class47.name;
					menuActionTypes[menuActionRow] = 1412;
					selectedMenuActions[menuActionRow] = class47.id << 14;
					firstMenuOperand[menuActionRow] = l;
					secondMenuOperand[menuActionRow] = i1;
					menuActionRow++;
				}
			}
			if (j1 == 1) {
				Npc class50_sub1_sub4_sub3_sub1 = npcs[k1];
				if (class50_sub1_sub4_sub3_sub1.npcDefinition.boundaryDimension == 1
						&& (class50_sub1_sub4_sub3_sub1.worldX & 0x7f) == 64
						&& (class50_sub1_sub4_sub3_sub1.worldY & 0x7f) == 64) {
					for (int i2 = 0; i2 < anInt1133; i2++) {
						Npc class50_sub1_sub4_sub3_sub1_1 = npcs[anIntArray1134[i2]];
						if (class50_sub1_sub4_sub3_sub1_1 != null
								&& class50_sub1_sub4_sub3_sub1_1 != class50_sub1_sub4_sub3_sub1
								&& class50_sub1_sub4_sub3_sub1_1.npcDefinition.boundaryDimension == 1
								&& class50_sub1_sub4_sub3_sub1_1.worldX == class50_sub1_sub4_sub3_sub1.worldX
								&& class50_sub1_sub4_sub3_sub1_1.worldY == class50_sub1_sub4_sub3_sub1.worldY)
							method82(class50_sub1_sub4_sub3_sub1_1.npcDefinition, i1, l, anIntArray1134[i2], (byte) -76);
					}

					for (int k2 = 0; k2 < localPlayerCount; k2++) {
						Player class50_sub1_sub4_sub3_sub2_1 = players[playerList[k2]];
						if (class50_sub1_sub4_sub3_sub2_1 != null
								&& class50_sub1_sub4_sub3_sub2_1.worldX == class50_sub1_sub4_sub3_sub1.worldX
								&& class50_sub1_sub4_sub3_sub2_1.worldY == class50_sub1_sub4_sub3_sub1.worldY)
							method38(playerList[k2], i1, l, class50_sub1_sub4_sub3_sub2_1, 0);
					}

				}
				method82(class50_sub1_sub4_sub3_sub1.npcDefinition, i1, l, k1, (byte) -76);
			}
			if (j1 == 0) {
				Player class50_sub1_sub4_sub3_sub2 = players[k1];
				if ((class50_sub1_sub4_sub3_sub2.worldX & 0x7f) == 64
						&& (class50_sub1_sub4_sub3_sub2.worldY & 0x7f) == 64) {
					for (int j2 = 0; j2 < anInt1133; j2++) {
						Npc class50_sub1_sub4_sub3_sub1_2 = npcs[anIntArray1134[j2]];
						if (class50_sub1_sub4_sub3_sub1_2 != null
								&& class50_sub1_sub4_sub3_sub1_2.npcDefinition.boundaryDimension == 1
								&& class50_sub1_sub4_sub3_sub1_2.worldX == class50_sub1_sub4_sub3_sub2.worldX
								&& class50_sub1_sub4_sub3_sub1_2.worldY == class50_sub1_sub4_sub3_sub2.worldY)
							method82(class50_sub1_sub4_sub3_sub1_2.npcDefinition, i1, l, anIntArray1134[j2], (byte) -76);
					}

					for (int l2 = 0; l2 < localPlayerCount; l2++) {
						Player class50_sub1_sub4_sub3_sub2_2 = players[playerList[l2]];
						if (class50_sub1_sub4_sub3_sub2_2 != null
								&& class50_sub1_sub4_sub3_sub2_2 != class50_sub1_sub4_sub3_sub2
								&& class50_sub1_sub4_sub3_sub2_2.worldX == class50_sub1_sub4_sub3_sub2.worldX
								&& class50_sub1_sub4_sub3_sub2_2.worldY == class50_sub1_sub4_sub3_sub2.worldY)
							method38(playerList[l2], i1, l, class50_sub1_sub4_sub3_sub2_2, 0);
					}

				}
				method38(k1, i1, l, class50_sub1_sub4_sub3_sub2, 0);
			}
			if (j1 == 3) {
				LinkedList class6 = groundItems[plane][l][i1];
				if (class6 != null) {
					for (Item class50_sub1_sub4_sub1 = (Item) class6.last(); class50_sub1_sub4_sub1 != null; class50_sub1_sub4_sub1 = (Item) class6
							.previous()) {
						ItemDefinition class16 = ItemDefinition.lookup(class50_sub1_sub4_sub1.itemId);
						if (itemSelected == 1) {
							menuActionTexts[menuActionRow] = "Use " + aString1150 + " with @lre@" + class16.name;
							menuActionTypes[menuActionRow] = 100;
							selectedMenuActions[menuActionRow] = class50_sub1_sub4_sub1.itemId;
							firstMenuOperand[menuActionRow] = l;
							secondMenuOperand[menuActionRow] = i1;
							menuActionRow++;
						} else if (widgetSelected == 1) {
							if ((anInt1173 & 1) == 1) {
								menuActionTexts[menuActionRow] = selectedWidgetName + " @lre@" + class16.name;
								menuActionTypes[menuActionRow] = 199;
								selectedMenuActions[menuActionRow] = class50_sub1_sub4_sub1.itemId;
								firstMenuOperand[menuActionRow] = l;
								secondMenuOperand[menuActionRow] = i1;
								menuActionRow++;
							}
						} else {
							for (int i3 = 4; i3 >= 0; i3--)
								if (class16.groundActions != null && class16.groundActions[i3] != null) {
									menuActionTexts[menuActionRow] = class16.groundActions[i3] + " @lre@" + class16.name;
									if (i3 == 0)
										menuActionTypes[menuActionRow] = 68;
									if (i3 == 1)
										menuActionTypes[menuActionRow] = 26;
									if (i3 == 2)
										menuActionTypes[menuActionRow] = 684;
									if (i3 == 3)
										menuActionTypes[menuActionRow] = 930;
									if (i3 == 4)
										menuActionTypes[menuActionRow] = 270;
									selectedMenuActions[menuActionRow] = class50_sub1_sub4_sub1.itemId;
									firstMenuOperand[menuActionRow] = l;
									secondMenuOperand[menuActionRow] = i1;
									menuActionRow++;
								} else if (i3 == 2) {
									menuActionTexts[menuActionRow] = "Take @lre@" + class16.name;
									menuActionTypes[menuActionRow] = 684;
									selectedMenuActions[menuActionRow] = class50_sub1_sub4_sub1.itemId;
									firstMenuOperand[menuActionRow] = l;
									secondMenuOperand[menuActionRow] = i1;
									menuActionRow++;
								}

							menuActionTexts[menuActionRow] = "Examine @lre@" + class16.name;
							menuActionTypes[menuActionRow] = 1564;
							selectedMenuActions[menuActionRow] = class50_sub1_sub4_sub1.itemId;
							firstMenuOperand[menuActionRow] = l;
							secondMenuOperand[menuActionRow] = i1;
							menuActionRow++;
						}
					}

				}
			}
		}

	}

	public void method44(boolean flag, int i) {
		if (!flag) {
			return;
		} else {
			Widget.method200(i);
			return;
		}
	}

	public void method45(int i, int j, int k, int l, int i1, int j1, byte byte0, int k1) {
		if (byte0 != aByte1066)
			anInt1175 = -380;
		if (j >= 1 && l >= 1 && j <= 102 && l <= 102) {
			if (lowMemory && i1 != plane)
				return;
			int l1 = 0;
			if (k1 == 0)
				l1 = currentScene.method267(i1, j, l);
			if (k1 == 1)
				l1 = currentScene.method268(j, (byte) 4, i1, l);
			if (k1 == 2)
				l1 = currentScene.method269(i1, j, l);
			if (k1 == 3)
				l1 = currentScene.getFloorDecorationHash(i1, j, l);
			if (l1 != 0) {
				int l2 = currentScene.method271(i1, j, l, l1);
				int i2 = l1 >> 14 & 0x7fff;
				int j2 = l2 & 0x1f;
				int k2 = l2 >> 6;
				if (k1 == 0) {
					currentScene.method258(l, i1, j, true);
					GameObjectDefinition class47 = GameObjectDefinition.getDefinition(i2);
					if (class47.solid)
						currentCollisionMap[i1].unmarkWall(k2, j, l, j2, class47.walkable);
				}
				if (k1 == 1)
					currentScene.method259(false, j, l, i1);
				if (k1 == 2) {
					currentScene.method260(l, i1, -779, j);
					GameObjectDefinition class47_1 = GameObjectDefinition.getDefinition(i2);
					if (j + class47_1.sizeX > 103 || l + class47_1.sizeX > 103 || j + class47_1.sizeY > 103
							|| l + class47_1.sizeY > 103)
						return;
					if (class47_1.solid)
						currentCollisionMap[i1].unmarkSolidOccupant(anInt1055, l, j, k2, class47_1.sizeY, class47_1.walkable,
								class47_1.sizeX);
				}
				if (k1 == 3) {
					currentScene.method261(j, l, true, i1);
					GameObjectDefinition class47_2 = GameObjectDefinition.getDefinition(i2);
					if (class47_2.solid && class47_2.actionsBoolean)
						currentCollisionMap[i1].unmarkConcealed(j, l);
				}
			}
			if (k >= 0) {
				int i3 = i1;
				if (i3 < 3 && (currentSceneTileFlags[1][j][l] & 2) == 2)
					i3++;
				Region.method165(k, i3, j1, l, currentCollisionMap[i1], i, j, 0, i1, currentScene,
						anIntArrayArrayArray891);
			}
		}
	}

	public void method46(int i, byte byte0, Buffer buffer) {
		buffer.initBitAccess();
		int j = buffer.getBits(8);
		if (byte0 != aByte1317)
			anInt1281 = -460;
		if (j < anInt1133) {
			for (int k = j; k < anInt1133; k++)
				removePlayers[removePlayerCount++] = anIntArray1134[k];

		}
		if (j > anInt1133) {
			SignLink.reportError(username + " Too many npcs");
			throw new RuntimeException("eek");
		}
		anInt1133 = 0;
		for (int l = 0; l < j; l++) {
			int i1 = anIntArray1134[l];
			Npc npc = npcs[i1];
			int updateRequired = buffer.getBits(1);
			if (updateRequired == 0) {
				anIntArray1134[anInt1133++] = i1;
				npc.pulseCycle = pulseCycle;
			} else {
				int moveType = buffer.getBits(2);
				if (moveType == 0) {
					anIntArray1134[anInt1133++] = i1;
					npc.pulseCycle = pulseCycle;
					updatedPlayers[updatedPlayerCount++] = i1;
				} else if (moveType == 1) {
					anIntArray1134[anInt1133++] = i1;
					npc.pulseCycle = pulseCycle;
					int direction = buffer.getBits(3);
					npc.move(direction, false);
					int blockUpdateRequired = buffer.getBits(1);
					if (blockUpdateRequired == 1)
						updatedPlayers[updatedPlayerCount++] = i1;
				} else if (moveType == 2) {
					anIntArray1134[anInt1133++] = i1;
					npc.pulseCycle = pulseCycle;
					int direction1 = buffer.getBits(3);
					npc.move(direction1, true);
					int direction2 = buffer.getBits(3);
					npc.move(direction2, true);
					int blockUpdateRequired = buffer.getBits(1);
					if (blockUpdateRequired == 1)
						updatedPlayers[updatedPlayerCount++] = i1;
				} else if (moveType == 3)
					removePlayers[removePlayerCount++] = i1;
			}
		}

	}

	public void method48(Buffer class50_sub1_sub2, boolean flag, int i) {
		loggedIn &= flag;
		removePlayerCount = 0;
		updatedPlayerCount = 0;
		method46(i, (byte) -58, class50_sub1_sub2);
		method132(class50_sub1_sub2, i, false);
		method62(class50_sub1_sub2, i, 838);
		for (int j = 0; j < removePlayerCount; j++) {
			int k = removePlayers[j];
			if (npcs[k].pulseCycle != pulseCycle) {
				npcs[k].npcDefinition = null;
				npcs[k] = null;
			}
		}

		if (class50_sub1_sub2.currentPosition != i) {
			SignLink.reportError(username + " size mismatch in getnpcpos - coord:" + class50_sub1_sub2.currentPosition
					+ " psize:" + i);
			throw new RuntimeException("eek");
		}
		for (int l = 0; l < anInt1133; l++)
			if (npcs[anIntArray1134[l]] == null) {
				SignLink.reportError(username + " null entry in npc list - coord:" + l + " size:" + anInt1133);
				throw new RuntimeException("eek");
			}

	}

	public void resetModelCaches() {
		GameObjectDefinition.modelCache.removeAll();
		GameObjectDefinition.animatedModelCache.removeAll();
		ActorDefinition.modelCache.removeAll();
		ItemDefinition.modelCache.removeAll();
		ItemDefinition.rgbImageCache.removeAll();
		Player.modelCache.removeAll();
		SpotAnimation.modelCache.removeAll();
	}


	public void method51(boolean flag) {
		Projectile class50_sub1_sub4_sub2 = (Projectile) aClass6_1282.first();
		if (flag)
			anInt1328 = 153;
		for (; class50_sub1_sub4_sub2 != null; class50_sub1_sub4_sub2 = (Projectile) aClass6_1282
				.next())
			if (class50_sub1_sub4_sub2.sceneId != plane || pulseCycle > class50_sub1_sub4_sub2.endCycle)
				class50_sub1_sub4_sub2.remove();
			else if (pulseCycle >= class50_sub1_sub4_sub2.delay) {
				if (class50_sub1_sub4_sub2.targetedEntityId > 0) {
					Npc class50_sub1_sub4_sub3_sub1 = npcs[class50_sub1_sub4_sub2.targetedEntityId - 1];
					if (class50_sub1_sub4_sub3_sub1 != null
							&& class50_sub1_sub4_sub3_sub1.worldX >= 0
							&& class50_sub1_sub4_sub3_sub1.worldX < 13312
							&& class50_sub1_sub4_sub3_sub1.worldY >= 0
							&& class50_sub1_sub4_sub3_sub1.worldY < 13312)
						class50_sub1_sub4_sub2.trackTarget(class50_sub1_sub4_sub3_sub1.worldX,
								class50_sub1_sub4_sub3_sub1.worldY, method110(
										class50_sub1_sub4_sub3_sub1.worldY,
										class50_sub1_sub4_sub3_sub1.worldX, (byte) 9,
										class50_sub1_sub4_sub2.sceneId)
										- class50_sub1_sub4_sub2.endHeight, pulseCycle);
				}
				if (class50_sub1_sub4_sub2.targetedEntityId < 0) {
					int i = -class50_sub1_sub4_sub2.targetedEntityId - 1;
					Player class50_sub1_sub4_sub3_sub2;
					if (i == thisPlayerServerId)
						class50_sub1_sub4_sub3_sub2 = localPlayer;
					else
						class50_sub1_sub4_sub3_sub2 = players[i];
					if (class50_sub1_sub4_sub3_sub2 != null
							&& class50_sub1_sub4_sub3_sub2.worldX >= 0
							&& class50_sub1_sub4_sub3_sub2.worldX < 13312
							&& class50_sub1_sub4_sub3_sub2.worldY >= 0
							&& class50_sub1_sub4_sub3_sub2.worldY < 13312)
						class50_sub1_sub4_sub2.trackTarget(class50_sub1_sub4_sub3_sub2.worldX,
								class50_sub1_sub4_sub3_sub2.worldY, method110(
										class50_sub1_sub4_sub3_sub2.worldY,
										class50_sub1_sub4_sub3_sub2.worldX, (byte) 9,
										class50_sub1_sub4_sub2.sceneId)
										- class50_sub1_sub4_sub2.endHeight, pulseCycle);
				}
				class50_sub1_sub4_sub2.move(tickDelta);
				currentScene.addEntity(-1, class50_sub1_sub4_sub2, (int) class50_sub1_sub4_sub2.currentX,
						(int) class50_sub1_sub4_sub2.currentHeight, false, 0, plane, 60,
						(int) class50_sub1_sub4_sub2.currentY, class50_sub1_sub4_sub2.anInt1562);
			}

		anInt1168++;
		if (anInt1168 > 51) {
			anInt1168 = 0;
			outBuffer.putOpcode(248);
		}
	}

	public void prepareTitle() {
		titleboxImage = new IndexedImage(titleArchive, "titlebox", 0);
		titleboxButtonImage = new IndexedImage(titleArchive, "titlebutton", 0);
		titleFlameEmblem = new IndexedImage[12];
		for (int i = 0; i < 12; i++)
			titleFlameEmblem[i] = new IndexedImage(titleArchive, "runes", i);

		anImageRGB1226 = new ImageRGB(128, 265);
		anImageRGB1227 = new ImageRGB(128, 265);
		System.arraycopy(flameLeftBackground.pixels, 0, anImageRGB1226.pixels, 0, (128 * 265));
		System.arraycopy(flameRightBackground.pixels, 0, anImageRGB1227.pixels, 0, (128 * 265));


		anIntArray1311 = new int[256];
		for (int l = 0; l < 64; l++)
			anIntArray1311[l] = l * 0x40000;

		for (int i1 = 0; i1 < 64; i1++)
			anIntArray1311[i1 + 64] = 0xff0000 + 1024 * i1;

		for (int j1 = 0; j1 < 64; j1++)
			anIntArray1311[j1 + 128] = 0xffff00 + 4 * j1;

		for (int k1 = 0; k1 < 64; k1++)
			anIntArray1311[k1 + 192] = 0xffffff;

		anIntArray1312 = new int[256];
		for (int l1 = 0; l1 < 64; l1++)
			anIntArray1312[l1] = l1 * 1024;

		for (int i2 = 0; i2 < 64; i2++)
			anIntArray1312[i2 + 64] = 65280 + 4 * i2;

		for (int j2 = 0; j2 < 64; j2++)
			anIntArray1312[j2 + 128] = 65535 + 0x40000 * j2;

		for (int k2 = 0; k2 < 64; k2++)
			anIntArray1312[k2 + 192] = 0xffffff;

		anIntArray1313 = new int[256];
		for (int l2 = 0; l2 < 64; l2++)
			anIntArray1313[l2] = l2 * 4;

		for (int i3 = 0; i3 < 64; i3++)
			anIntArray1313[i3 + 64] = 255 + 0x40000 * i3;

		for (int j3 = 0; j3 < 64; j3++)
			anIntArray1313[j3 + 128] = 0xff00ff + 1024 * j3;

		for (int k3 = 0; k3 < 64; k3++)
			anIntArray1313[k3 + 192] = 0xffffff;

		anIntArray1310 = new int[256];
		anIntArray1176 = new int[32768];
		anIntArray1177 = new int[32768];
		method83(null, 0);
		anIntArray1084 = new int[32768];
		anIntArray1085 = new int[32768];
		drawLoadingText(10, "Connecting to fileserver");
		if (!aBoolean1243) {
			aBoolean1314 = true;
			aBoolean1243 = true;
			startRunnable(this, 2);
		}
	}

	public void removeFriend(long l) {
		try {
			if (l == 0L)
				return;
			for (int j = 0; j < friendsCount; j++) {
				if (friends[j] != l)
					continue;
				friendsCount--;
				redrawTabArea = true;
				for (int k = j; k < friendsCount; k++) {
					friendUsernames[k] = friendUsernames[k + 1];
					friendWorlds[k] = friendWorlds[k + 1];
					friends[k] = friends[k + 1];
				}

				outBuffer.putOpcode(141);
				outBuffer.putLong(l);
				break;
			}

		} catch (RuntimeException runtimeexception) {
			SignLink.reportError("38799, " + l + ", " + runtimeexception.toString());
		}
		throw new RuntimeException();
	}

	public void processMenuClick() {
		if (activeInterfaceType != 0)
			return;
		int meta = super.clickType;
		if (widgetSelected == 1 && super.clickX >= 516 && super.clickY >= 160 && super.clickX <= 765
				&& super.clickY <= 205)
			meta = 0;
		if (menuOpen) {
			if (meta != 1) {
				int x = super.mouseX;
				int y = super.mouseY;
				if (anInt1304 == 0) {
					x -= 4;
					y -= 4;
				}
				if (anInt1304 == 1) {
					x -= 553;
					y -= 205;
				}
				if (anInt1304 == 2) {
					x -= 17;
					y -= 357;
				}
				if (x < menuClickX - 10 || x > menuClickX + anInt1307 + 10 || y < menuClickY - 10
						|| y > menuClickY + anInt1308 + 10) {
					menuOpen = false;
					if (anInt1304 == 1)
						redrawTabArea = true;
					if (anInt1304 == 2)
						redrawChatbox = true;
				}
			}
			if (meta == 1) {
				int menuX = menuClickX;
				int menuY = menuClickY;
				int dx = anInt1307;
				int x = super.clickX;
				int y = super.clickY;
				if (anInt1304 == 0) {
					x -= 4;
					y -= 4;
				}
				if (anInt1304 == 1) {
					x -= 553;
					y -= 205;
				}
				if (anInt1304 == 2) {
					x -= 17;
					y -= 357;
				}
				int id = -1;
				for (int row = 0; row < menuActionRow; row++) {
					int k3 = menuY + 31 + (menuActionRow - 1 - row) * 15;
					if (x > menuX && x < menuX + dx && y > k3 - 13 && y < k3 + 3)
						id = row;
				}

				if (id != -1)
					processMenuActions(id);
				menuOpen = false;
				if (anInt1304 == 1)
					redrawTabArea = true;
				if (anInt1304 == 2) {
					redrawChatbox = true;
					return;
				}
			}
		} else {
			if (meta == 1 && menuActionRow > 0) {
				int action = menuActionTypes[menuActionRow - 1];
				if (action == 9 || action == 225 || action == 444 || action == 564 || action == 894 || action == 961 || action == 399 || action == 324
						|| action == 227 || action == 891 || action == 52 || action == Actions.EXAMINE_ITEM) {
					int item = firstMenuOperand[menuActionRow - 1];
					int id = secondMenuOperand[menuActionRow - 1];
					Widget widget = Widget.forId(id);
					if (widget.itemSwapable || widget.itemDeletesDraged) {
						aBoolean1155 = false;
						anInt1269 = 0;
						modifiedWidgetId = id;
						selectedInventorySlot = item;
						activeInterfaceType = 2;
						anInt1114 = super.clickX;
						anInt1115 = super.clickY;
						if (Widget.forId(id).parentId == openInterfaceId)
							activeInterfaceType = 1;
						if (Widget.forId(id).parentId == backDialogueId)
							activeInterfaceType = 3;
						return;
					}
				}
			}
			if (meta == 1 && (anInt1300 == 1 || menuHasAddFriend(menuActionRow - 1, aByte1161)) && menuActionRow > 2)
				meta = 2;
			if (meta == 1 && menuActionRow > 0)
				processMenuActions(menuActionRow - 1);
			if (meta == 2 && menuActionRow > 0)
				determineMenuSize();
		}
	}

	public void drawMinimap(ImageRGB sprite, int x, int y) {
		int r = x * x + y * y;

		if (r > 4225 && r < 0x15f90) {
			int theta = cameraHorizontal + anInt916 & 0x7ff;
			int sin = Model.SINE[theta];
			int cos = Model.COSINE[theta];
			sin = (sin * 256) / (anInt1233 + 256);
			cos = (cos * 256) / (anInt1233 + 256);
			int l1 = y * sin + x * cos >> 16;
			int i2 = y * cos - x * sin >> 16;
			double d = Math.atan2(l1, i2);
			int j2 = (int) (Math.sin(d) * 63D);
			int k2 = (int) (Math.cos(d) * 57D);
			minimapEdge.method466(256, 15, (94 + j2 + 4) - 10, 15, 20, anInt1119, 20, d, 83 - k2 - 20);
			return;
		} else {
			drawOnMinimap(sprite, x, y);
			return;
		}
	}

	public void method56(boolean flag, int i, int j, int k, int l, int i1) {
		scrollbarUp.drawImage(j, i1);
		scrollbarDown.drawImage(j, (i1 + k) - 16);
		Rasterizer.drawFilledRectangle(j, i1 + 16, 16, k - 32, anInt931);
		int j1 = ((k - 32) * k) / l;
		if (j1 < 8)
			j1 = 8;
		int k1 = ((k - 32 - j1) * i) / (l - k);
		Rasterizer.drawFilledRectangle(j, i1 + 16 + k1, 16, j1, anInt1080);
		Rasterizer.drawVerticalLine(j, i1 + 16 + k1, j1, anInt1135);
		Rasterizer.drawVerticalLine(j + 1, i1 + 16 + k1, j1, anInt1135);
		if (!flag)
			anInt921 = -136;
		Rasterizer.drawHorizontalLine(j, i1 + 16 + k1, 16, anInt1135);
		Rasterizer.drawHorizontalLine(j, i1 + 17 + k1, 16, anInt1135);
		Rasterizer.drawVerticalLine(j + 15, i1 + 16 + k1, j1, anInt1287);
		Rasterizer.drawVerticalLine(j + 14, i1 + 17 + k1, j1 - 1, anInt1287);
		Rasterizer.drawHorizontalLine(j, i1 + 15 + k1 + j1, 16, anInt1287);
		Rasterizer.drawHorizontalLine(j + 1, i1 + 14 + k1 + j1, 15, anInt1287);
	}

	public void method57(int i, boolean flag) {
		i = 26 / i;
		for (int j = 0; j < anInt1133; j++) {
			Npc class50_sub1_sub4_sub3_sub1 = npcs[anIntArray1134[j]];
			int k = 0x20000000 + (anIntArray1134[j] << 14);
			if (class50_sub1_sub4_sub3_sub1 == null || !class50_sub1_sub4_sub3_sub1.isVisible()
					|| class50_sub1_sub4_sub3_sub1.npcDefinition.visible != flag
					|| !class50_sub1_sub4_sub3_sub1.npcDefinition.method360())
				continue;
			int l = class50_sub1_sub4_sub3_sub1.worldX >> 7;
			int i1 = class50_sub1_sub4_sub3_sub1.worldY >> 7;
			if (l < 0 || l >= 104 || i1 < 0 || i1 >= 104)
				continue;
			if (class50_sub1_sub4_sub3_sub1.boundaryDimension == 1
					&& (class50_sub1_sub4_sub3_sub1.worldX & 0x7f) == 64
					&& (class50_sub1_sub4_sub3_sub1.worldY & 0x7f) == 64) {
				if (anIntArrayArray886[l][i1] == anInt1138)
					continue;
				anIntArrayArray886[l][i1] = anInt1138;
			}
			if (!class50_sub1_sub4_sub3_sub1.npcDefinition.clickable)
				k += 0x80000000;
			currentScene.addEntity(k, class50_sub1_sub4_sub3_sub1,
					class50_sub1_sub4_sub3_sub1.worldX, method110(
							class50_sub1_sub4_sub3_sub1.worldY,
							class50_sub1_sub4_sub3_sub1.worldX, (byte) 9, plane),
					class50_sub1_sub4_sub3_sub1.aBoolean1592, 0, plane,
					(class50_sub1_sub4_sub3_sub1.boundaryDimension - 1) * 64 + 60,
					class50_sub1_sub4_sub3_sub1.worldY,
					class50_sub1_sub4_sub3_sub1.anInt1612);
		}

	}

	public void setWaveVolume( int j) {
		SignLink.waveVolume = j;
	}

	public void dropClient() {
		if (anInt873 > 0) {
			logout();
			return;
		}
		method125("Please wait - attempting to reestablish", "Connection lost");
		minimapState = 0;
		destinationX = 0;
		BufferedConnection class17 = gameConnection;
		loggedIn = false;
		anInt850 = 0;
		login(username, password, true);
		if (!loggedIn)
			logout();
		try {
			class17.close();
			return;
		} catch (Exception _ex) {
			return;
		}
	}

	public boolean handleWidgetDynamicAction(Widget widget) {
		int type = widget.contentType;
		if (friendListStatus == 2) {
			if (type == 201) {
				redrawChatbox = true;
				inputType = 0;
				messagePromptRaised = true;
				chatMessage = "";
				friendsListAction = 1;
				chatboxInputMessage = "Enter name of friend to add to list";
			}
			if (type == 202) {
				redrawChatbox = true;
				inputType = 0;
				messagePromptRaised = true;
				chatMessage = "";
				friendsListAction = 2;
				chatboxInputMessage = "Enter name of friend to delete from list";
			}
		}
		if (type == 205) {
			anInt873 = 250;
			return true;
		}
		if (type == 501) {
			redrawChatbox = true;
			inputType = 0;
			messagePromptRaised = true;
			chatMessage = "";
			friendsListAction = 4;
			chatboxInputMessage = "Enter name of player to add to list";
		}
		if (type == 502) {
			redrawChatbox = true;
			inputType = 0;
			messagePromptRaised = true;
			chatMessage = "";
			friendsListAction = 5;
			chatboxInputMessage = "Enter name of player to delete from list";
		}
		if (type >= 300 && type <= 313) {
			int k = (type - 300) / 2;
			int j1 = type & 1;
			int i2 = characterEditIdentityKits[k];
			if (i2 != -1) {
				do {
					if (j1 == 0 && --i2 < 0)
						i2 = IdentityKit.count - 1;
					if (j1 == 1 && ++i2 >= IdentityKit.count)
						i2 = 0;
				} while (IdentityKit.cache[i2].widgetDisplayed
						|| IdentityKit.cache[i2].partId != k + (characterEditChangeGenger ? 0 : 7));
				characterEditIdentityKits[k] = i2;
				aBoolean1277 = true;
			}
		}
		if (type >= 314 && type <= 323) {
			int l = (type - 314) / 2;
			int k1 = type & 1;
			int j2 = characterEditColors[l];
			if (k1 == 0 && --j2 < 0)
				j2 = playerColours[l].length - 1;
			if (k1 == 1 && ++j2 >= playerColours[l].length)
				j2 = 0;
			characterEditColors[l] = j2;
			aBoolean1277 = true;
		}
		if (type == 324 && !characterEditChangeGenger) {
			characterEditChangeGenger = true;
			method25();
		}
		if (type == 325 && characterEditChangeGenger) {
			characterEditChangeGenger = false;
			method25();
		}
		if (type == 326) {
			outBuffer.putOpcode(163);
			outBuffer.putByte(characterEditChangeGenger ? 0 : 1);
			for (int i1 = 0; i1 < 7; i1++)
				outBuffer.putByte(characterEditIdentityKits[i1]);

			for (int l1 = 0; l1 < 5; l1++)
				outBuffer.putByte(characterEditColors[l1]);

			return true;
		}
		if (type == 620)
			reportMutePlayer = !reportMutePlayer;
		if (type >= 601 && type <= 613) {
			closeWidgets();
			if (reportedName.length() > 0) {
				outBuffer.putOpcode(184);
				outBuffer.putLong(TextUtils.nameToLong(reportedName));
				outBuffer.putByte(type - 601);
				outBuffer.putByte(reportMutePlayer ? 1 : 0);
			}
		}
		return false;
	}

	private Archive requestArchive(int id, String file, int expectedCrc, int x, String displayName) {
		byte[] archiveBuffer = null;
		int reconnectionDelay = 5;

		try {
			if (stores[0] != null)
				archiveBuffer = stores[0].get(id);
		} catch (Exception ignored) {}

		if (archiveBuffer != null && Configuration.JAGGRAB_ENABLED) {
			archiveCrc.reset();
			archiveCrc.update(archiveBuffer);

			int calculatedCrc = (int) archiveCrc.getValue();

			if (calculatedCrc != expectedCrc)
				archiveBuffer = null;
		}

		if (archiveBuffer != null)
			return new Archive(archiveBuffer);

		int attempts = 0;

		while (archiveBuffer == null) {
			String error = "Unknown error";

			drawLoadingText(x, "Requesting " + displayName);

			try {
				int currentPercentage = 0;
				DataInputStream jaggrabStream = openJaggrabStream(file + expectedCrc);
				byte[] bytes = new byte[6];

				jaggrabStream.readFully(bytes, 0, 6);

				Buffer buffer = new Buffer(bytes);
				buffer.currentPosition = 3;
				int archiveLength = buffer.get24BitInt() + 6;
				int archiveRead = 6;
				archiveBuffer = new byte[archiveLength];

				System.arraycopy(bytes, 0, archiveBuffer, 0, 6);

				while (archiveRead < archiveLength) {
					int remaining = archiveLength - archiveRead;

					if (remaining > 1000)
						remaining = 1000;

					int read = jaggrabStream.read(archiveBuffer, archiveRead, remaining);

					if (read < 0) {
						error = "Length error: " + archiveRead + "/" + archiveLength;
						throw new IOException("EOF");
					}

					archiveRead += read;
					int calculatedPercentage = (archiveRead * 100) / archiveLength;

					if (calculatedPercentage != currentPercentage)
						drawLoadingText(x, "Loading " + displayName + " - " + calculatedPercentage + "%");

					currentPercentage = calculatedPercentage;
				}

				jaggrabStream.close();

				try {
					if (stores[0] != null)
						stores[0].put(archiveBuffer.length, archiveBuffer, id);
				} catch (Exception _ex) {
					stores[0] = null;
				}

				if (Configuration.JAGGRAB_ENABLED) {
					archiveCrc.reset();
					archiveCrc.update(archiveBuffer);

					int calculatedCrc = (int) archiveCrc.getValue();

					if (calculatedCrc != expectedCrc) {
						archiveBuffer = null;
						attempts++;
						error = "Checksum error: " + calculatedCrc;
					}
				}
			} catch (IOException ex) {
				if (error.equals("Unknown error"))
					error = "Connection error";

				archiveBuffer = null;
			} catch (NullPointerException ex) {
				error = "Null error";
				archiveBuffer = null;

				if (!SignLink.reportError)
					return null;
			} catch (ArrayIndexOutOfBoundsException ex) {
				error = "Bounds error";
				archiveBuffer = null;

				if (!SignLink.reportError)
					return null;
			} catch (Exception ex) {
				error = "Unexpected error";
				archiveBuffer = null;

				if (!SignLink.reportError)
					return null;
			}

			if (archiveBuffer == null) {
				for (int time = reconnectionDelay; time > 0; time--) {
					if (attempts >= 3) {
						drawLoadingText(x, "Game updated - please reload page");
						time = 10;
					} else {
						drawLoadingText(x, error + " - Retrying in " + time);
					}

					try {
						Thread.sleep(1000L);
					} catch (Exception ignored) {}
				}

				reconnectionDelay *= 2;

				if (reconnectionDelay > 60)
					reconnectionDelay = 60;

				useJaggrab = !useJaggrab;
			}
		}

		return new Archive(archiveBuffer);
	}

	public void redraw() {
		aBoolean1046 = true;
	}

	public void method62(Buffer class50_sub1_sub2, int i, int j) {
		j = 24 / j;
		for (int k = 0; k < updatedPlayerCount; k++) {
			int l = updatedPlayers[k];
			Npc class50_sub1_sub4_sub3_sub1 = npcs[l];
			int i1 = class50_sub1_sub2.getUnsignedByte();
			if ((i1 & 1) != 0) {
				class50_sub1_sub4_sub3_sub1.npcDefinition = ActorDefinition.getDefinition(class50_sub1_sub2.method550());
				class50_sub1_sub4_sub3_sub1.boundaryDimension = class50_sub1_sub4_sub3_sub1.npcDefinition.boundaryDimension;
				class50_sub1_sub4_sub3_sub1.anInt1600 = class50_sub1_sub4_sub3_sub1.npcDefinition.degreesToTurn;
				class50_sub1_sub4_sub3_sub1.walkAnimationId = class50_sub1_sub4_sub3_sub1.npcDefinition.walkAnimationId;
				class50_sub1_sub4_sub3_sub1.turnAroundAnimationId = class50_sub1_sub4_sub3_sub1.npcDefinition.turnAroundAnimationId;
				class50_sub1_sub4_sub3_sub1.turnRightAnimationId = class50_sub1_sub4_sub3_sub1.npcDefinition.turnRightAnimationId;
				class50_sub1_sub4_sub3_sub1.turnLeftAnimationId = class50_sub1_sub4_sub3_sub1.npcDefinition.turnLeftAnimationId;
				class50_sub1_sub4_sub3_sub1.idleAnimation = class50_sub1_sub4_sub3_sub1.npcDefinition.standAnimationId;
			}
			if ((i1 & 0x40) != 0) {
				class50_sub1_sub4_sub3_sub1.anInt1609 = class50_sub1_sub2.method549();
				if (class50_sub1_sub4_sub3_sub1.anInt1609 == 65535)
					class50_sub1_sub4_sub3_sub1.anInt1609 = -1;
			}
			if ((i1 & 0x80) != 0) {
				int j1 = class50_sub1_sub2.getByteAdded();
				int j2 = class50_sub1_sub2.getByteAdded();
				class50_sub1_sub4_sub3_sub1.updateHits(j2, j1, pulseCycle);
				class50_sub1_sub4_sub3_sub1.endCycle = pulseCycle + 300;
				class50_sub1_sub4_sub3_sub1.anInt1596 = class50_sub1_sub2.getUnsignedByte();
				class50_sub1_sub4_sub3_sub1.anInt1597 = class50_sub1_sub2.getByteSubtracted();
			}
			if ((i1 & 4) != 0) {
				class50_sub1_sub4_sub3_sub1.graphic = class50_sub1_sub2.getUnsignedLEShort();
				int k1 = class50_sub1_sub2.method556();
				class50_sub1_sub4_sub3_sub1.spotAnimationDelay = k1 >> 16;
				class50_sub1_sub4_sub3_sub1.anInt1617 = pulseCycle + (k1 & 0xffff);
				class50_sub1_sub4_sub3_sub1.currentAnimation = 0;
				class50_sub1_sub4_sub3_sub1.anInt1616 = 0;
				if (class50_sub1_sub4_sub3_sub1.anInt1617 > pulseCycle)
					class50_sub1_sub4_sub3_sub1.currentAnimation = -1;
				if (class50_sub1_sub4_sub3_sub1.graphic == 65535)
					class50_sub1_sub4_sub3_sub1.graphic = -1;
			}
			if ((i1 & 0x20) != 0) {
				class50_sub1_sub4_sub3_sub1.forcedChat = class50_sub1_sub2.getString();
				class50_sub1_sub4_sub3_sub1.textCycle = 100;
			}
			if ((i1 & 8) != 0) {
				class50_sub1_sub4_sub3_sub1.anInt1598 = class50_sub1_sub2.getLittleShortA();
				class50_sub1_sub4_sub3_sub1.anInt1599 = class50_sub1_sub2.method549();
			}
			if ((i1 & 2) != 0) {
				int l1 = class50_sub1_sub2.getUnsignedLEShort();
				if (l1 == 65535)
					l1 = -1;
				int k2 = class50_sub1_sub2.getByteSubtracted();
				if (l1 == class50_sub1_sub4_sub3_sub1.emoteAnimation && l1 != -1) {
					int i3 = AnimationSequence.animations[l1].anInt307;
					if (i3 == 1) {
						class50_sub1_sub4_sub3_sub1.displayedEmoteFrames = 0;
						class50_sub1_sub4_sub3_sub1.anInt1626 = 0;
						class50_sub1_sub4_sub3_sub1.animationDelay = k2;
						class50_sub1_sub4_sub3_sub1.anInt1628 = 0;
					}
					if (i3 == 2)
						class50_sub1_sub4_sub3_sub1.anInt1628 = 0;
				} else if (l1 == -1
						|| class50_sub1_sub4_sub3_sub1.emoteAnimation == -1
						|| AnimationSequence.animations[l1].anInt301 >= AnimationSequence.animations[class50_sub1_sub4_sub3_sub1.emoteAnimation].anInt301) {
					class50_sub1_sub4_sub3_sub1.emoteAnimation = l1;
					class50_sub1_sub4_sub3_sub1.displayedEmoteFrames = 0;
					class50_sub1_sub4_sub3_sub1.anInt1626 = 0;
					class50_sub1_sub4_sub3_sub1.animationDelay = k2;
					class50_sub1_sub4_sub3_sub1.anInt1628 = 0;
					class50_sub1_sub4_sub3_sub1.anInt1613 = class50_sub1_sub4_sub3_sub1.pathLength;
				}
			}
			if ((i1 & 0x10) != 0) {
				int i2 = class50_sub1_sub2.getByteSubtracted();
				int l2 = class50_sub1_sub2.getByteSubtracted();
				class50_sub1_sub4_sub3_sub1.updateHits(l2, i2, pulseCycle);
				class50_sub1_sub4_sub3_sub1.endCycle = pulseCycle + 300;
				class50_sub1_sub4_sub3_sub1.anInt1596 = class50_sub1_sub2.getUnsignedByte();
				class50_sub1_sub4_sub3_sub1.anInt1597 = class50_sub1_sub2.getByteNegated();
			}
		}

	}

	private void parsePlayerBlock(int id, Player player, int mask, Buffer buffer) {
		if ((mask & 8) != 0) {
			int animation = buffer.getUnsignedLEShort();

			if (animation == 65535)
				animation = -1;

			int delay = buffer.getByteSubtracted();

			if (animation == player.emoteAnimation && animation != -1) {
				int mode = AnimationSequence.animations[animation].anInt307;

				if (mode == 1) {
					player.displayedEmoteFrames = 0;
					player.anInt1626 = 0;
					player.animationDelay = delay;
					player.anInt1628 = 0;
				}

				if (mode == 2)
					player.anInt1628 = 0;
			} else if (animation == -1 || player.emoteAnimation == -1
					|| AnimationSequence.animations[animation].anInt301 >= AnimationSequence.animations[player.emoteAnimation].anInt301) {
				player.emoteAnimation = animation;
				player.displayedEmoteFrames = 0;
				player.anInt1626 = 0;
				player.animationDelay = delay;
				player.anInt1628 = 0;
				player.anInt1613 = player.pathLength;
			}
		}

		if ((mask & 0x10) != 0) {
			player.forcedChat = buffer.getString();

			if (player.forcedChat.charAt(0) == '~') {
				player.forcedChat = player.forcedChat.substring(1);
				addChatMessage(player.playerName, player.forcedChat, 2);
			} else if (player == localPlayer) {
                addChatMessage(player.playerName, player.forcedChat, 2);
            }

			player.textColour = 0;
			player.textEffect = 0;
			player.textCycle = 150;
		}

		if ((mask & 0x100) != 0) {
			player.anInt1602 = buffer.getByteAdded();
			player.anInt1604 = buffer.getByteNegated();
			player.anInt1603 = buffer.getByteSubtracted();
			player.anInt1605 = buffer.getUnsignedByte();
			player.anInt1606 = buffer.getUnsignedLEShort() + pulseCycle;
			player.anInt1607 = buffer.method550() + pulseCycle;
			player.anInt1608 = buffer.getUnsignedByte();

			player.resetPath();
		}

		if ((mask & 1) != 0) {
			player.anInt1609 = buffer.method550();

			if (player.anInt1609 == 65535)
				player.anInt1609 = -1;
		}

		if ((mask & 2) != 0) {
			player.anInt1598 = buffer.getUnsignedLEShort();
			player.anInt1599 = buffer.getUnsignedLEShort();
		}

		if ((mask & 0x200) != 0) {
			player.graphic = buffer.method550();
			int heightAndDelay = buffer.method556();
			player.spotAnimationDelay = heightAndDelay >> 16;
			player.anInt1617 = pulseCycle + (heightAndDelay & 0xffff);
			player.currentAnimation = 0;
			player.anInt1616 = 0;

			if (player.anInt1617 > pulseCycle)
				player.currentAnimation = -1;

			if (player.graphic == 65535)
				player.graphic = -1;
		}

		if ((mask & 4) != 0) {
			int size = buffer.getUnsignedByte();
			byte bytes[] = new byte[size];
			Buffer appearance = new Buffer(bytes);

			buffer.getBytesReverse(bytes, 0, size);

			cachedAppearances[id] = appearance;

			player.updateAppearance(appearance);
		}

		if ((mask & 0x400) != 0) {
			int damage = buffer.getByteAdded();
			int type = buffer.getByteSubtracted();

			player.updateHits(type, damage, pulseCycle);

			player.endCycle = pulseCycle + 300;
			player.anInt1596 = buffer.getByteNegated();
			player.anInt1597 = buffer.getUnsignedByte();
		}

		if ((mask & 0x40) != 0) {
			int effectsAndColour = buffer.getUnsignedLEShort();
			int rights = buffer.getByteNegated();
			int length = buffer.getByteAdded();
			int currentPosition = buffer.currentPosition;

			if (player.playerName != null && player.visible) {
				long nameLong = TextUtils.nameToLong(player.playerName);
				boolean ignored = false;

				if (rights <= 1) {
					for (int i = 0; i < ignoresCount; i++) {
						if (ignores[i] != nameLong)
							continue;

						ignored = true;
						break;
					}

				}

				if (!ignored && !inTutorialIsland) {
                    try {
                        chatBuffer.currentPosition = 0;

                        buffer.getBytesAdded(chatBuffer.buffer, 0, length);

                        chatBuffer.currentPosition = 0;
                        String message = ChatCensor.censorString(ChatEncoder.get(length, chatBuffer));
                        player.forcedChat = message;
                        player.textColour = effectsAndColour >> 8;
                        player.textEffect = effectsAndColour & 0xff;
                        player.textCycle = 150;

                        if (rights == 2 || rights == 3)
                            addChatMessage("@cr2@" + player.playerName, message, 1);
                        else if (rights == 1)
                            addChatMessage("@cr1@" + player.playerName, message, 1);
                        else
                            addChatMessage(player.playerName, message, 2);
                    } catch (Exception exception) {
                        SignLink.reportError("cde2");
                    }
                }
			}

			buffer.currentPosition = currentPosition + length;
		}

		if ((mask & 0x80) != 0) {
			int damage = buffer.getByteSubtracted();
			int type = buffer.getByteNegated();

			player.updateHits(type, damage, pulseCycle);

			player.endCycle = pulseCycle + 300;
			player.anInt1596 = buffer.getByteSubtracted();
			player.anInt1597 = buffer.getUnsignedByte();
		}
	}

	public void resetTitleScreen() {
		if (aClass18_1198 != null)
			return;

		super.imageProducer = null;
		chatboxProducingGraphicsBuffer = null;
		aClass18_1157 = null;
		aClass18_1156 = null;
		aClass18_1158 = null;
		aClass18_1108 = null;
		aClass18_1109 = null;

		flameLeftBackground = new ProducingGraphicsBuffer(128, 265, getParentComponent());
		Rasterizer.resetPixels();

		flameRightBackground = new ProducingGraphicsBuffer(128, 265, getParentComponent());
		Rasterizer.resetPixels();

		aClass18_1198 = new ProducingGraphicsBuffer(509, 171, getParentComponent());
		Rasterizer.resetPixels();

		aClass18_1199 = new ProducingGraphicsBuffer(360, 132, getParentComponent());
		Rasterizer.resetPixels();

		aClass18_1200 = new ProducingGraphicsBuffer(360, 200, getParentComponent());
		Rasterizer.resetPixels();

		aClass18_1203 = new ProducingGraphicsBuffer(202, 238, getParentComponent());
		Rasterizer.resetPixels();

		aClass18_1204 = new ProducingGraphicsBuffer(203, 238, getParentComponent());
		Rasterizer.resetPixels();

		aClass18_1205 = new ProducingGraphicsBuffer(74, 94, getParentComponent());
		Rasterizer.resetPixels();

		aClass18_1206 = new ProducingGraphicsBuffer(75, 94, getParentComponent());
		Rasterizer.resetPixels();

		if (titleArchive != null) {
			prepareTitleBackground();
			prepareTitle();
		}

		aBoolean1046 = true;
	}

	public void startup() {
		drawLoadingText(20, "Starting up");

		if (SignLink.cacheData != null) {
			for (int type = 0; type < 5; type++)
				stores[type] = new Index(type + 1, 0x927c0, SignLink.cacheData, SignLink.cacheIndex[type]);
		}

		try {
		    if (Configuration.JAGGRAB_ENABLED)
			    requestArchiveCrcs();

			titleArchive = requestArchive(1, "title", archiveHashes[1], 25, "title screen");
			fontSmall = new TypeFace(false, titleArchive, "p11_full");
			fontNormal = new TypeFace(false, titleArchive, "p12_full");
			fontBold = new TypeFace(false, titleArchive, "b12_full");
			fontFancy = new TypeFace(true, titleArchive, "q8_full");

			prepareTitleBackground();
			prepareTitle();

			Archive configArchive = requestArchive(2, "config", archiveHashes[2], 30, "config");
			Archive archiveInterface = requestArchive(3, "interface", archiveHashes[3], 35, "interface");
			Archive archiveMedia = requestArchive(4, "media", archiveHashes[4], 40, "2d gameGraphics");
			Archive textureArchive = requestArchive(6, "textures", archiveHashes[6], 45, "textures");
			Archive chatArchive = requestArchive(7, "wordenc", archiveHashes[7], 50, "chat system");
			Archive soundArchive = requestArchive(8, "sounds", archiveHashes[8], 55, "sound effects");
			currentSceneTileFlags = new byte[4][104][104];
			anIntArrayArrayArray891 = new int[4][105][105];
			currentScene = new Scene(anIntArrayArrayArray891, 104, 4, 104, (byte) 5);

			for (int j = 0; j < 4; j++)
				currentCollisionMap[j] = new CollisionMap(104, 104);

			minimapImage = new ImageRGB(512, 512);
			Archive versionListArchive = requestArchive(5, "versionlist", archiveHashes[5], 60, "update list");

			drawLoadingText(60, "Connecting to update server");

			onDemandRequester = new OnDemandRequester();
			onDemandRequester.init(versionListArchive, this);

			Animation.method235(onDemandRequester.animCount());
			Model.init(onDemandRequester.fileCount(0), onDemandRequester);

			if (!lowMemory) {
				nextSong = 0;

				try {
					nextSong = Integer.parseInt(getParameter("music"));
				} catch(Exception ignored) {}

                songChanging = true;

				onDemandRequester.request(2, nextSong);

				while (onDemandRequester.method333() > 0) {
					method77(false);

					try {
						Thread.sleep(100L);
					} catch (Exception ignored) {}

					if (onDemandRequester.requestFails > 3) {
						method19("ondemand");
						return;
					}
				}
			}

			drawLoadingText(65, "Requesting animations");

			int fileRequestCount = onDemandRequester.fileCount(1);

			for (int i = 0; i < fileRequestCount; i++)
				onDemandRequester.request(1, i);

			while (onDemandRequester.method333() > 0) {
				int total = fileRequestCount - onDemandRequester.method333();

				if (total > 0)
					drawLoadingText(65, "Loading animations - " + (total * 100) / fileRequestCount + "%");

				method77(false);

				try {
					Thread.sleep(100L);
				} catch (Exception ignored) {}

				if (onDemandRequester.requestFails > 3) {
					method19("ondemand");
					return;
				}
			}

			drawLoadingText(70, "Requesting models");

			fileRequestCount = onDemandRequester.fileCount(0);

			for (int i = 0; i < fileRequestCount; i++) {
				int id = onDemandRequester.modelId(i);

				if ((id & 1) != 0)
					onDemandRequester.request(0, i);
			}

			fileRequestCount = onDemandRequester.method333();

			while (onDemandRequester.method333() > 0) {
				int total = fileRequestCount - onDemandRequester.method333();

				if (total > 0)
					drawLoadingText(70, "Loading models - " + (total * 100) / fileRequestCount + "%");

				method77(false);

				try {
					Thread.sleep(100L);
				} catch (Exception ignored) {}
			}

			if (stores[0] != null) {
				drawLoadingText(75, "Requesting maps");
				onDemandRequester.request(3, onDemandRequester.regId(0, 47, 48, 0)); // these are the maps around tutorial island
				onDemandRequester.request(3, onDemandRequester.regId(0, 47, 48, 1));
				onDemandRequester.request(3, onDemandRequester.regId(0, 48, 48, 0));
				onDemandRequester.request(3, onDemandRequester.regId(0, 48, 48, 1));
				onDemandRequester.request(3, onDemandRequester.regId(0, 49, 48, 0));
				onDemandRequester.request(3, onDemandRequester.regId(0, 49, 48, 1));
				onDemandRequester.request(3, onDemandRequester.regId(0, 47, 47, 0));
				onDemandRequester.request(3, onDemandRequester.regId(0, 47, 47, 1));
				onDemandRequester.request(3, onDemandRequester.regId(0, 48, 47, 0));
				onDemandRequester.request(3, onDemandRequester.regId(0, 48, 47, 1));
				onDemandRequester.request(3, onDemandRequester.regId(0, 48, 148, 0));
				onDemandRequester.request(3, onDemandRequester.regId(0, 48, 148, 1));

				fileRequestCount = onDemandRequester.method333();

				while (onDemandRequester.method333() > 0) {
					int total = fileRequestCount - onDemandRequester.method333();

					if (total > 0)
						drawLoadingText(75, "Loading maps - " + (total * 100) / fileRequestCount + "%");

					method77(false);

					try {
						Thread.sleep(100L);
					} catch (Exception ignored) {}
				}
			}

			fileRequestCount = onDemandRequester.fileCount(0);

			for (int i = 0; i < fileRequestCount; i++) {
				int id = onDemandRequester.modelId(i);
				byte priority = 0;

				if ((id & 8) != 0)
					priority = 10;
				else if ((id & 0x20) != 0)
					priority = 9;
				else if ((id & 0x10) != 0)
					priority = 8;
				else if ((id & 0x40) != 0)
					priority = 7;
				else if ((id & 0x80) != 0)
					priority = 6;
				else if ((id & 2) != 0)
					priority = 5;
				else if ((id & 4) != 0)
					priority = 4;
				if ((id & 1) != 0)
					priority = 3;

				if (priority != 0)
					onDemandRequester.setPriority(priority, 0, i);
			}

			onDemandRequester.preloadRegions(memberServer);

			if (!lowMemory) {
				fileRequestCount = onDemandRequester.fileCount(2);

				for (int i = 1; i < fileRequestCount; i++) {
                    if (onDemandRequester.midiIdEqualsOne(i))
                        onDemandRequester.setPriority((byte) 1, 2, i);
                }
			}

			fileRequestCount = onDemandRequester.fileCount(0);

			for (int i = 0; i < fileRequestCount; i++) {
				int id = onDemandRequester.modelId(i);

				if (id == 0 && onDemandRequester.anInt1350 < 200)
					onDemandRequester.setPriority((byte) 1, 0, i);
			}

			drawLoadingText(80, "Unpacking media");

			inventoryBackgroundImage = new IndexedImage(archiveMedia, "invback", 0);
			chatboxBackgroundImage = new IndexedImage(archiveMedia, "chatback", 0);
			minimapBackgroundImage = new IndexedImage(archiveMedia, "mapback", 0);
			anIndexedImage1052 = new IndexedImage(archiveMedia, "backbase1", 0);
			anIndexedImage1053 = new IndexedImage(archiveMedia, "backbase2", 0);
			anIndexedImage1054 = new IndexedImage(archiveMedia, "backhmid1", 0);

			for (int i = 0; i < 13; i++)
				tabIcon[i] = new IndexedImage(archiveMedia, "sideicons", i);

			minimapCompass = new ImageRGB(archiveMedia, "compass", 0);
			minimapEdge = new ImageRGB(archiveMedia, "mapedge", 0);
			minimapEdge.trim();

			for (int i = 0; i < 72; i++)
				aClass50_Sub1_Sub1_Sub3Array1153[i] = new IndexedImage(archiveMedia, "mapscene", i);

			for (int i = 0; i < 70; i++)
				worldMapHintIcons[i] = new ImageRGB(archiveMedia, "mapfunction", i);

			for (int i = 0; i < 5; i++)
				aClass50_Sub1_Sub1_Sub1Array1182[i] = new ImageRGB(archiveMedia, "hitmarks", i);

			for (int i = 0; i < 6; i++)
				aClass50_Sub1_Sub1_Sub1Array1288[i] = new ImageRGB(archiveMedia, "headicons_pk", i);

			for (int i = 0; i < 9; i++)
				aClass50_Sub1_Sub1_Sub1Array1079[i] = new ImageRGB(archiveMedia, "headicons_prayer", i);

			for (int i = 0; i < 6; i++)
				aClass50_Sub1_Sub1_Sub1Array954[i] = new ImageRGB(archiveMedia, "headicons_hint", i);

			aClass50_Sub1_Sub1_Sub1_1086 = new ImageRGB(archiveMedia, "overlay_multiway", 0);
			mapFlagMarker = new ImageRGB(archiveMedia, "mapmarker", 0);
			aClass50_Sub1_Sub1_Sub1_1037 = new ImageRGB(archiveMedia, "mapmarker", 1);

			for (int i = 0; i < 8; i++)
				cursorCross[i] = new ImageRGB(archiveMedia, "cross", i);

			mapdotItem = new ImageRGB(archiveMedia, "mapdots", 0);
			mapdotActor = new ImageRGB(archiveMedia, "mapdots", 1);
			mapdotPlayer = new ImageRGB(archiveMedia, "mapdots", 2);
			mapdotFriend = new ImageRGB(archiveMedia, "mapdots", 3);
			mapdotTeammate = new ImageRGB(archiveMedia, "mapdots", 4);
			scrollbarUp = new IndexedImage(archiveMedia, "scrollbar", 0);
			scrollbarDown = new IndexedImage(archiveMedia, "scrollbar", 1);
			aClass50_Sub1_Sub1_Sub3_880 = new IndexedImage(archiveMedia, "redstone1", 0);
			aClass50_Sub1_Sub1_Sub3_881 = new IndexedImage(archiveMedia, "redstone2", 0);
			aClass50_Sub1_Sub1_Sub3_882 = new IndexedImage(archiveMedia, "redstone3", 0);
			aClass50_Sub1_Sub1_Sub3_883 = new IndexedImage(archiveMedia, "redstone1", 0);
			aClass50_Sub1_Sub1_Sub3_883.flipHorizontal();

			aClass50_Sub1_Sub1_Sub3_884 = new IndexedImage(archiveMedia, "redstone2", 0);
			aClass50_Sub1_Sub1_Sub3_884.flipHorizontal();

			aClass50_Sub1_Sub1_Sub3_983 = new IndexedImage(archiveMedia, "redstone1", 0);
			aClass50_Sub1_Sub1_Sub3_983.flipVertical();

			aClass50_Sub1_Sub1_Sub3_984 = new IndexedImage(archiveMedia, "redstone2", 0);
			aClass50_Sub1_Sub1_Sub3_984.flipVertical();

			aClass50_Sub1_Sub1_Sub3_985 = new IndexedImage(archiveMedia, "redstone3", 0);
			aClass50_Sub1_Sub1_Sub3_985.flipVertical();

			aClass50_Sub1_Sub1_Sub3_986 = new IndexedImage(archiveMedia, "redstone1", 0);
			aClass50_Sub1_Sub1_Sub3_986.flipHorizontal();
			aClass50_Sub1_Sub1_Sub3_986.flipVertical();

			aClass50_Sub1_Sub1_Sub3_987 = new IndexedImage(archiveMedia, "redstone2", 0);
			aClass50_Sub1_Sub1_Sub3_987.flipHorizontal();
			aClass50_Sub1_Sub1_Sub3_987.flipVertical();

			for (int i = 0; i < 2; i++)
				moderatorIcon[i] = new IndexedImage(archiveMedia, "mod_icons", i);

			ImageRGB image = new ImageRGB(archiveMedia, "backleft1", 0);
			aClass18_906 = new ProducingGraphicsBuffer(image.width, image.height, getParentComponent());
			image.drawInverse(0, 0);

			image = new ImageRGB(archiveMedia, "backleft2", 0);
			aClass18_907 = new ProducingGraphicsBuffer(image.width, image.height, getParentComponent());
			image.drawInverse(0, 0);

			image = new ImageRGB(archiveMedia, "backright1", 0);
			aClass18_908 = new ProducingGraphicsBuffer(image.width, image.height, getParentComponent());
			image.drawInverse(0, 0);

			image = new ImageRGB(archiveMedia, "backright2", 0);
			aClass18_909 = new ProducingGraphicsBuffer(image.width, image.height, getParentComponent());
			image.drawInverse(0, 0);

			image = new ImageRGB(archiveMedia, "backtop1", 0);
			aClass18_910 = new ProducingGraphicsBuffer(image.width, image.height, getParentComponent());
			image.drawInverse(0, 0);

			image = new ImageRGB(archiveMedia, "backvmid1", 0);
			aClass18_911 = new ProducingGraphicsBuffer(image.width, image.height, getParentComponent());
			image.drawInverse(0, 0);

			image = new ImageRGB(archiveMedia, "backvmid2", 0);
			aClass18_912 = new ProducingGraphicsBuffer(image.width, image.height, getParentComponent());
			image.drawInverse(0, 0);

			image = new ImageRGB(archiveMedia, "backvmid3", 0);
			aClass18_913 = new ProducingGraphicsBuffer(image.width, image.height, getParentComponent());
			image.drawInverse(0, 0);

			image = new ImageRGB(archiveMedia, "backhmid2", 0);
			aClass18_914 = new ProducingGraphicsBuffer(image.width, image.height, getParentComponent());
			image.drawInverse(0, 0);

            int offset = (int) (Math.random() * 41D) - 20;
			int red = (int) ((Math.random() * 21D) - 10) + offset;
			int green = (int) ((Math.random() * 21D) - 10) + offset;
            int blue = (int) ((Math.random() * 21D) - 10) + offset;

			for (int i = 0; i < 100; i++) {
				if (worldMapHintIcons[i] != null)
					worldMapHintIcons[i].adjustRGB(red, green, blue);

				if (aClass50_Sub1_Sub1_Sub3Array1153[i] != null)
					aClass50_Sub1_Sub1_Sub3Array1153[i].mixPalette(red, green, blue);
			}

			drawLoadingText(83, "Unpacking textures");
			Rasterizer3D.loadIndexedImages(textureArchive);
			Rasterizer3D.method501(0.80000000000000004D);
			Rasterizer3D.method496(20);
			drawLoadingText(86, "Unpacking config");
			AnimationSequence.load(configArchive);
			GameObjectDefinition.load(configArchive);
			FloorDefinition.load(configArchive);
			ItemDefinition.load(configArchive);
			ActorDefinition.load(configArchive);
			IdentityKit.load(configArchive);
			SpotAnimation.load(configArchive);
			Varp.load(configArchive);
			Varbit.load(configArchive);

			ItemDefinition.memberServer = memberServer;

			if (!lowMemory) {
				drawLoadingText(90, "Unpacking sounds");

				byte[] bytes = soundArchive.getFile("sounds.dat");
				Buffer buffer = new Buffer(bytes);

				SoundTrack.load(buffer);
			}

			drawLoadingText(95, "Unpacking interfaces");

			TypeFace[] typefaces = {fontSmall, fontNormal, fontBold, fontFancy};

			Widget.load(archiveInterface, typefaces, archiveMedia);
			drawLoadingText(100, "Preparing game engine");

			for (int y = 0; y < 33; y++) {
				int minWidth = 999;
				int maxWidth = 0;

				for (int x = 0; x < 34; x++) {
					if (minimapBackgroundImage.pixels[x + y * minimapBackgroundImage.width] == 0) {
						if (minWidth == 999)
							minWidth = x;

						continue;
					}

					if (minWidth == 999)
						continue;

					maxWidth = x;
					break;
				}

				anIntArray1180[y] = minWidth;
				anIntArray1286[y] = maxWidth - minWidth;
			}

			for (int y = 5; y < 156; y++) {
				int minWidth = 999;
				int maxWidth = 0;

				for (int x = 25; x < 172; x++) {
					if (minimapBackgroundImage.pixels[x + y * minimapBackgroundImage.width] == 0
                            && (x > 34 || y > 34)) {
						if (minWidth == 999)
							minWidth = x;

						continue;
					}

					if (minWidth == 999)
						continue;

					maxWidth = x;
					break;
				}

				anIntArray1019[y - 5] = minWidth - 25;
				anIntArray920[y - 5] = maxWidth - minWidth;
			}

			Rasterizer3D.method494(765, 503);
			anIntArray1003 = Rasterizer3D.lineOffsets;

			Rasterizer3D.method494(479, 96);
			chatboxLineOffsets = Rasterizer3D.lineOffsets;

			Rasterizer3D.method494(190, 261);
			anIntArray1001 = Rasterizer3D.lineOffsets;

			Rasterizer3D.method494(512, 334);
			anIntArray1002 = Rasterizer3D.lineOffsets;

			int ai[] = new int[9];

			for (int i = 0; i < 9; i++) { //TODO: Needs refactoring
				int j9 = 128 + i * 32 + 15;
				int k9 = 600 + j9 * 3;
				int l9 = Rasterizer3D.SINE[j9];
				ai[i] = k9 * l9 >> 16;
			}

			Scene.method277(500, 800, 512, 334, ai);
			ChatCensor.load(chatArchive);

			mouseCapturer = new MouseCapturer(this);

			startRunnable(mouseCapturer, 10);

			GameObject.client = this;
			GameObjectDefinition.client = this;
			ActorDefinition.client = this;
			return;
		} catch (Exception exception) {
			SignLink.reportError("loaderror " + aString1027 + " " + anInt1322);
		}

		aBoolean1283 = true;
	}

	public void method65(int i) {
		if (!lowMemory) {
			for (int k = 0; k < anIntArray1290.length; k++) {
				int l = anIntArray1290[k];
				if (Rasterizer3D.anIntArray1546[l] >= i) {
					IndexedImage class50_sub1_sub1_sub3 = Rasterizer3D.aClass50_Sub1_Sub1_Sub3Array1540[l];
					int i1 = class50_sub1_sub1_sub3.width * class50_sub1_sub1_sub3.height - 1;
					int j1 = class50_sub1_sub1_sub3.width * tickDelta * 2;
					byte abyte0[] = class50_sub1_sub1_sub3.pixels;
					byte abyte1[] = aByteArray1245;
					for (int k1 = 0; k1 <= i1; k1++)
						abyte1[k1] = abyte0[k1 - j1 & i1];

					class50_sub1_sub1_sub3.pixels = abyte1;
					aByteArray1245 = abyte0;
					Rasterizer3D.method499(l, 9);
				}
			}

		}
	}

	public void method66(int i, Widget class13, int j, int k, int l, int i1, int j1, int k1) {
		if (j1 != 23658)
			return;
		if (class13.type != 0 || class13.children == null || class13.hiddenUntilHovered)
			return;
		if (i1 < l || k1 < i || i1 > l + class13.width || k1 > i + class13.height)
			return;
		int l1 = class13.children.length;
		for (int i2 = 0; i2 < l1; i2++) {
			int j2 = class13.childrenX[i2] + l;
			int k2 = (class13.childrenY[i2] + i) - k;
			Widget child = Widget.forId(class13.children[i2]);
			j2 += child.anInt228;
			k2 += child.anInt259;
			if ((child.hoveredPopup >= 0 || child.disabledHoveredColor != 0) && i1 >= j2 && k1 >= k2
					&& i1 < j2 + child.width && k1 < k2 + child.height)
				if (child.hoveredPopup >= 0)
					anInt915 = child.hoveredPopup;
				else
					anInt915 = child.id;
			if (child.type == 8 && i1 >= j2 && k1 >= k2 && i1 < j2 + child.width
					&& k1 < k2 + child.height)
				anInt1315 = child.id;
			if (child.type == 0) {
				method66(k2, child, j, child.anInt231, j2, i1, 23658, k1);
				if (child.scrollLimit > child.height)
					method42(child.scrollLimit, k2, child, (byte) 102, k1, j, i1, child.height, j2
							+ child.width);
			} else {
				if (child.actionType == 1 && i1 >= j2 && k1 >= k2 && i1 < j2 + child.width
						&& k1 < k2 + child.height) {
					boolean flag = false;
					if (child.contentType != 0)
						flag = processFriendListClick(child);
					if (!flag) {
						menuActionTexts[menuActionRow] = child.tooltip;
						menuActionTypes[menuActionRow] = 352;
						secondMenuOperand[menuActionRow] = child.id;
						menuActionRow++;
					}
				}
				if (child.actionType == 2 && widgetSelected == 0 && i1 >= j2 && k1 >= k2 && i1 < j2 + child.width
						&& k1 < k2 + child.height) {
					String circumfix = child.optionCircumfix;
					if (circumfix.indexOf(" ") != -1)
						circumfix = circumfix.substring(0, circumfix.indexOf(" "));
					menuActionTexts[menuActionRow] = circumfix + " @gre@" + child.optionText;
					menuActionTypes[menuActionRow] = Actions.USABLE_WIDGET;
					secondMenuOperand[menuActionRow] = child.id;
					menuActionRow++;
				}
				if (child.actionType == 3 && i1 >= j2 && k1 >= k2 && i1 < j2 + child.width
						&& k1 < k2 + child.height) {
					menuActionTexts[menuActionRow] = "Close";
					if (j == 3)
						menuActionTypes[menuActionRow] = 55;
					else
						menuActionTypes[menuActionRow] = Actions.CLOSE_WIDGETS;
					secondMenuOperand[menuActionRow] = child.id;
					menuActionRow++;
				}
				if (child.actionType == 4 && i1 >= j2 && k1 >= k2 && i1 < j2 + child.width
						&& k1 < k2 + child.height) {
					menuActionTexts[menuActionRow] = child.tooltip;
					menuActionTypes[menuActionRow] = 890;
					secondMenuOperand[menuActionRow] = child.id;
					menuActionRow++;
				}
				if (child.actionType == 5 && i1 >= j2 && k1 >= k2 && i1 < j2 + child.width
						&& k1 < k2 + child.height) {
					menuActionTexts[menuActionRow] = child.tooltip;
					menuActionTypes[menuActionRow] = 518;
					secondMenuOperand[menuActionRow] = child.id;
					menuActionRow++;
				}
				if (child.actionType == 6 && !aBoolean1239 && i1 >= j2 && k1 >= k2 && i1 < j2 + child.width
						&& k1 < k2 + child.height) {
					menuActionTexts[menuActionRow] = child.tooltip;
					menuActionTypes[menuActionRow] = Actions.CLICK_TO_CONTINUE;
					secondMenuOperand[menuActionRow] = child.id;
					menuActionRow++;
				}
				if (child.type == 2) {
					int l2 = 0;
					for (int i3 = 0; i3 < child.height; i3++) {
						for (int j3 = 0; j3 < child.width; j3++) {
							int k3 = j2 + j3 * (32 + child.itemSpritePadsX);
							int l3 = k2 + i3 * (32 + child.itemSpritePadsY);
							if (l2 < 20) {
								k3 += child.imageX[l2];
								l3 += child.imageY[l2];
							}
							if (i1 >= k3 && k1 >= l3 && i1 < k3 + 32 && k1 < l3 + 32) {
								mouseInvInterfaceIndex = l2;
								lastActiveInvInterface = child.id;
								if (child.items[l2] > 0) {
									ItemDefinition definition = ItemDefinition.lookup(child.items[l2] - 1);
									if (itemSelected == 1 && child.isInventory) {
										if (child.id != anInt1148 || l2 != anInt1147) {
											menuActionTexts[menuActionRow] = "Use " + aString1150 + " with @lre@"
													+ definition.name;
											menuActionTypes[menuActionRow] = 903;
											selectedMenuActions[menuActionRow] = definition.id;
											firstMenuOperand[menuActionRow] = l2;
											secondMenuOperand[menuActionRow] = child.id;
											menuActionRow++;
										}
									} else if (widgetSelected == 1 && child.isInventory) {
										if ((anInt1173 & 0x10) == 16) {
											menuActionTexts[menuActionRow] = selectedWidgetName + " @lre@" + definition.name;
											menuActionTypes[menuActionRow] = 361;
											selectedMenuActions[menuActionRow] = definition.id;
											firstMenuOperand[menuActionRow] = l2;
											secondMenuOperand[menuActionRow] = child.id;
											menuActionRow++;
										}
									} else {
										if (child.isInventory) {
											for (int i4 = 4; i4 >= 3; i4--)
												if (definition.inventoryActions != null
														&& definition.inventoryActions[i4] != null) {
													menuActionTexts[menuActionRow] = definition.inventoryActions[i4]
															+ " @lre@" + definition.name;
													if (i4 == 3)
														menuActionTypes[menuActionRow] = 227;
													if (i4 == 4)
														menuActionTypes[menuActionRow] = 891;
													selectedMenuActions[menuActionRow] = definition.id;
													firstMenuOperand[menuActionRow] = l2;
													secondMenuOperand[menuActionRow] = child.id;
													menuActionRow++;
												} else if (i4 == 4) {
													menuActionTexts[menuActionRow] = "Drop @lre@" + definition.name;
													menuActionTypes[menuActionRow] = 891;
													selectedMenuActions[menuActionRow] = definition.id;
													firstMenuOperand[menuActionRow] = l2;
													secondMenuOperand[menuActionRow] = child.id;
													menuActionRow++;
												}

										}
										if (child.itemUsable) {
											menuActionTexts[menuActionRow] = "Use @lre@" + definition.name;
											menuActionTypes[menuActionRow] = 52;
											selectedMenuActions[menuActionRow] = definition.id;
											firstMenuOperand[menuActionRow] = l2;
											secondMenuOperand[menuActionRow] = child.id;
											menuActionRow++;
										}
										if (child.isInventory && definition.inventoryActions != null) {
											for (int j4 = 2; j4 >= 0; j4--)
												if (definition.inventoryActions[j4] != null) {
													menuActionTexts[menuActionRow] = definition.inventoryActions[j4]
															+ " @lre@" + definition.name;
													if (j4 == 0)
														menuActionTypes[menuActionRow] = 961;
													if (j4 == 1)
														menuActionTypes[menuActionRow] = 399;
													if (j4 == 2)
														menuActionTypes[menuActionRow] = 324;
													selectedMenuActions[menuActionRow] = definition.id;
													firstMenuOperand[menuActionRow] = l2;
													secondMenuOperand[menuActionRow] = child.id;
													menuActionRow++;
												}

										}
										if (child.options != null) {
											for (int k4 = 4; k4 >= 0; k4--)
												if (child.options[k4] != null) {
													menuActionTexts[menuActionRow] = child.options[k4]
															+ " @lre@" + definition.name;
													if (k4 == 0)
														menuActionTypes[menuActionRow] = 9;
													if (k4 == 1)
														menuActionTypes[menuActionRow] = 225;
													if (k4 == 2)
														menuActionTypes[menuActionRow] = 444;
													if (k4 == 3)
														menuActionTypes[menuActionRow] = 564;
													if (k4 == 4)
														menuActionTypes[menuActionRow] = 894;
													selectedMenuActions[menuActionRow] = definition.id;
													firstMenuOperand[menuActionRow] = l2;
													secondMenuOperand[menuActionRow] = child.id;
													menuActionRow++;
												}

										}
										menuActionTexts[menuActionRow] = "Examine @lre@" + definition.name;
										menuActionTypes[menuActionRow] = Actions.EXAMINE_ITEM;
										selectedMenuActions[menuActionRow] = definition.id;
										firstMenuOperand[menuActionRow] = l2;
										secondMenuOperand[menuActionRow] = child.id;
										menuActionRow++;
									}
								}
							}
							l2++;
						}

					}

				}
			}
		}

	}

	public void method67(int i) {
		for (int j = 0; j < anInt1133; j++) {
			int k = anIntArray1134[j];
			Npc class50_sub1_sub4_sub3_sub1 = npcs[k];
			if (class50_sub1_sub4_sub3_sub1 != null)
				method68(class50_sub1_sub4_sub3_sub1.npcDefinition.boundaryDimension, (byte) -97, class50_sub1_sub4_sub3_sub1);
		}

		if (i != -37214)
			outBuffer.putByte(41);
	}

	public void method68(int i, byte byte0, Actor class50_sub1_sub4_sub3) {
		if (class50_sub1_sub4_sub3.worldX < 128 || class50_sub1_sub4_sub3.worldY < 128
				|| class50_sub1_sub4_sub3.worldX >= 13184 || class50_sub1_sub4_sub3.worldY >= 13184) {
			class50_sub1_sub4_sub3.emoteAnimation = -1;
			class50_sub1_sub4_sub3.graphic = -1;
			class50_sub1_sub4_sub3.anInt1606 = 0;
			class50_sub1_sub4_sub3.anInt1607 = 0;
			class50_sub1_sub4_sub3.worldX = class50_sub1_sub4_sub3.pathX[0] * 128
					+ class50_sub1_sub4_sub3.boundaryDimension * 64;
			class50_sub1_sub4_sub3.worldY = class50_sub1_sub4_sub3.pathY[0] * 128
					+ class50_sub1_sub4_sub3.boundaryDimension * 64;
			class50_sub1_sub4_sub3.resetPath();
		}
		if (class50_sub1_sub4_sub3 == localPlayer
				&& (class50_sub1_sub4_sub3.worldX < 1536 || class50_sub1_sub4_sub3.worldY < 1536
						|| class50_sub1_sub4_sub3.worldX >= 11776 || class50_sub1_sub4_sub3.worldY >= 11776)) {
			class50_sub1_sub4_sub3.emoteAnimation = -1;
			class50_sub1_sub4_sub3.graphic = -1;
			class50_sub1_sub4_sub3.anInt1606 = 0;
			class50_sub1_sub4_sub3.anInt1607 = 0;
			class50_sub1_sub4_sub3.worldX = class50_sub1_sub4_sub3.pathX[0] * 128
					+ class50_sub1_sub4_sub3.boundaryDimension * 64;
			class50_sub1_sub4_sub3.worldY = class50_sub1_sub4_sub3.pathY[0] * 128
					+ class50_sub1_sub4_sub3.boundaryDimension * 64;
			class50_sub1_sub4_sub3.resetPath();
		}
		if (class50_sub1_sub4_sub3.anInt1606 > pulseCycle)
			method69(class50_sub1_sub4_sub3, true);
		else if (class50_sub1_sub4_sub3.anInt1607 >= pulseCycle)
			method70(class50_sub1_sub4_sub3, -31135);
		else
			method71(class50_sub1_sub4_sub3, 0);
		method72((byte) 8, class50_sub1_sub4_sub3);
		method73(class50_sub1_sub4_sub3, -136);
		if (byte0 == -97)
			;
	}

	public void method69(Actor class50_sub1_sub4_sub3, boolean flag) {
		if (!flag)
			aBoolean963 = !aBoolean963;
		int i = class50_sub1_sub4_sub3.anInt1606 - pulseCycle;
		int j = class50_sub1_sub4_sub3.anInt1602 * 128 + class50_sub1_sub4_sub3.boundaryDimension * 64;
		int k = class50_sub1_sub4_sub3.anInt1604 * 128 + class50_sub1_sub4_sub3.boundaryDimension * 64;
		class50_sub1_sub4_sub3.worldX += (j - class50_sub1_sub4_sub3.worldX) / i;
		class50_sub1_sub4_sub3.worldY += (k - class50_sub1_sub4_sub3.worldY) / i;
		class50_sub1_sub4_sub3.anInt1623 = 0;
		if (class50_sub1_sub4_sub3.anInt1608 == 0)
			class50_sub1_sub4_sub3.nextStepOrientation = 1024;
		if (class50_sub1_sub4_sub3.anInt1608 == 1)
			class50_sub1_sub4_sub3.nextStepOrientation = 1536;
		if (class50_sub1_sub4_sub3.anInt1608 == 2)
			class50_sub1_sub4_sub3.nextStepOrientation = 0;
		if (class50_sub1_sub4_sub3.anInt1608 == 3)
			class50_sub1_sub4_sub3.nextStepOrientation = 512;
	}

	public void method70(Actor class50_sub1_sub4_sub3, int i) {
		if (class50_sub1_sub4_sub3.anInt1607 == pulseCycle
				|| class50_sub1_sub4_sub3.emoteAnimation == -1
				|| class50_sub1_sub4_sub3.animationDelay != 0
				|| class50_sub1_sub4_sub3.anInt1626 + 1 > AnimationSequence.animations[class50_sub1_sub4_sub3.emoteAnimation]
						.getFrameLength(class50_sub1_sub4_sub3.displayedEmoteFrames)) {
			int j = class50_sub1_sub4_sub3.anInt1607 - class50_sub1_sub4_sub3.anInt1606;
			int k = pulseCycle - class50_sub1_sub4_sub3.anInt1606;
			int l = class50_sub1_sub4_sub3.anInt1602 * 128 + class50_sub1_sub4_sub3.boundaryDimension * 64;
			int i1 = class50_sub1_sub4_sub3.anInt1604 * 128 + class50_sub1_sub4_sub3.boundaryDimension * 64;
			int j1 = class50_sub1_sub4_sub3.anInt1603 * 128 + class50_sub1_sub4_sub3.boundaryDimension * 64;
			int k1 = class50_sub1_sub4_sub3.anInt1605 * 128 + class50_sub1_sub4_sub3.boundaryDimension * 64;
			class50_sub1_sub4_sub3.worldX = (l * (j - k) + j1 * k) / j;
			class50_sub1_sub4_sub3.worldY = (i1 * (j - k) + k1 * k) / j;
		}
		class50_sub1_sub4_sub3.anInt1623 = 0;
		if (class50_sub1_sub4_sub3.anInt1608 == 0)
			class50_sub1_sub4_sub3.nextStepOrientation = 1024;
		if (class50_sub1_sub4_sub3.anInt1608 == 1)
			class50_sub1_sub4_sub3.nextStepOrientation = 1536;
		if (class50_sub1_sub4_sub3.anInt1608 == 2)
			class50_sub1_sub4_sub3.nextStepOrientation = 0;
		if (class50_sub1_sub4_sub3.anInt1608 == 3)
			class50_sub1_sub4_sub3.nextStepOrientation = 512;
		class50_sub1_sub4_sub3.anInt1612 = class50_sub1_sub4_sub3.nextStepOrientation;
		if (i == -31135)
			;
	}

	public void method71(Actor class50_sub1_sub4_sub3, int i) {
		class50_sub1_sub4_sub3.movementAnimation = class50_sub1_sub4_sub3.idleAnimation;
		if (class50_sub1_sub4_sub3.pathLength == 0) {
			class50_sub1_sub4_sub3.anInt1623 = 0;
			return;
		}
		if (class50_sub1_sub4_sub3.emoteAnimation != -1 && class50_sub1_sub4_sub3.animationDelay == 0) {
			AnimationSequence class14 = AnimationSequence.animations[class50_sub1_sub4_sub3.emoteAnimation];
			if (class50_sub1_sub4_sub3.anInt1613 > 0 && class14.anInt305 == 0) {
				class50_sub1_sub4_sub3.anInt1623++;
				return;
			}
			if (class50_sub1_sub4_sub3.anInt1613 <= 0 && class14.priority == 0) {
				class50_sub1_sub4_sub3.anInt1623++;
				return;
			}
		}
		int j = class50_sub1_sub4_sub3.worldX;
		int k = class50_sub1_sub4_sub3.worldY;
		int l = class50_sub1_sub4_sub3.pathX[class50_sub1_sub4_sub3.pathLength - 1] * 128
				+ class50_sub1_sub4_sub3.boundaryDimension * 64;
		int i1 = class50_sub1_sub4_sub3.pathY[class50_sub1_sub4_sub3.pathLength - 1] * 128
				+ class50_sub1_sub4_sub3.boundaryDimension * 64;
		if (l - j > 256 || l - j < -256 || i1 - k > 256 || i1 - k < -256) {
			class50_sub1_sub4_sub3.worldX = l;
			class50_sub1_sub4_sub3.worldY = i1;
			return;
		}
		if (j < l) {
			if (k < i1)
				class50_sub1_sub4_sub3.nextStepOrientation = 1280;
			else if (k > i1)
				class50_sub1_sub4_sub3.nextStepOrientation = 1792;
			else
				class50_sub1_sub4_sub3.nextStepOrientation = 1536;
		} else if (j > l) {
			if (k < i1)
				class50_sub1_sub4_sub3.nextStepOrientation = 768;
			else if (k > i1)
				class50_sub1_sub4_sub3.nextStepOrientation = 256;
			else
				class50_sub1_sub4_sub3.nextStepOrientation = 512;
		} else if (k < i1)
			class50_sub1_sub4_sub3.nextStepOrientation = 1024;
		else
			class50_sub1_sub4_sub3.nextStepOrientation = 0;
		int j1 = class50_sub1_sub4_sub3.nextStepOrientation - class50_sub1_sub4_sub3.anInt1612 & 0x7ff;
		if (j1 > 1024)
			j1 -= 2048;
		int k1 = class50_sub1_sub4_sub3.turnAroundAnimationId;
		if (i != 0)
			outBuffer.putByte(34);
		if (j1 >= -256 && j1 <= 256)
			k1 = class50_sub1_sub4_sub3.walkAnimationId;
		else if (j1 >= 256 && j1 < 768)
			k1 = class50_sub1_sub4_sub3.turnLeftAnimationId;
		else if (j1 >= -768 && j1 <= -256)
			k1 = class50_sub1_sub4_sub3.turnRightAnimationId;
		if (k1 == -1)
			k1 = class50_sub1_sub4_sub3.walkAnimationId;
		class50_sub1_sub4_sub3.movementAnimation = k1;
		int l1 = 4;
		if (class50_sub1_sub4_sub3.anInt1612 != class50_sub1_sub4_sub3.nextStepOrientation
				&& class50_sub1_sub4_sub3.anInt1609 == -1 && class50_sub1_sub4_sub3.anInt1600 != 0)
			l1 = 2;
		if (class50_sub1_sub4_sub3.pathLength > 2)
			l1 = 6;
		if (class50_sub1_sub4_sub3.pathLength > 3)
			l1 = 8;
		if (class50_sub1_sub4_sub3.anInt1623 > 0 && class50_sub1_sub4_sub3.pathLength > 1) {
			l1 = 8;
			class50_sub1_sub4_sub3.anInt1623--;
		}
		if (class50_sub1_sub4_sub3.runningQueue[class50_sub1_sub4_sub3.pathLength - 1])
			l1 <<= 1;
		if (l1 >= 8 && class50_sub1_sub4_sub3.movementAnimation == class50_sub1_sub4_sub3.walkAnimationId
				&& class50_sub1_sub4_sub3.runAnimationId != -1)
			class50_sub1_sub4_sub3.movementAnimation = class50_sub1_sub4_sub3.runAnimationId;
		if (j < l) {
			class50_sub1_sub4_sub3.worldX += l1;
			if (class50_sub1_sub4_sub3.worldX > l)
				class50_sub1_sub4_sub3.worldX = l;
		} else if (j > l) {
			class50_sub1_sub4_sub3.worldX -= l1;
			if (class50_sub1_sub4_sub3.worldX < l)
				class50_sub1_sub4_sub3.worldX = l;
		}
		if (k < i1) {
			class50_sub1_sub4_sub3.worldY += l1;
			if (class50_sub1_sub4_sub3.worldY > i1)
				class50_sub1_sub4_sub3.worldY = i1;
		} else if (k > i1) {
			class50_sub1_sub4_sub3.worldY -= l1;
			if (class50_sub1_sub4_sub3.worldY < i1)
				class50_sub1_sub4_sub3.worldY = i1;
		}
		if (class50_sub1_sub4_sub3.worldX == l && class50_sub1_sub4_sub3.worldY == i1) {
			class50_sub1_sub4_sub3.pathLength--;
			if (class50_sub1_sub4_sub3.anInt1613 > 0)
				class50_sub1_sub4_sub3.anInt1613--;
		}
	}

	public void method72(byte byte0, Actor class50_sub1_sub4_sub3) {
		if (byte0 != 8)
			anInt928 = incomingRandom.nextInt();
		if (class50_sub1_sub4_sub3.anInt1600 == 0)
			return;
		if (class50_sub1_sub4_sub3.anInt1609 != -1 && class50_sub1_sub4_sub3.anInt1609 < 32768) {
			Npc class50_sub1_sub4_sub3_sub1 = npcs[class50_sub1_sub4_sub3.anInt1609];
			if (class50_sub1_sub4_sub3_sub1 != null) {
				int l = class50_sub1_sub4_sub3.worldX - class50_sub1_sub4_sub3_sub1.worldX;
				int j1 = class50_sub1_sub4_sub3.worldY - class50_sub1_sub4_sub3_sub1.worldY;
				if (l != 0 || j1 != 0)
					class50_sub1_sub4_sub3.nextStepOrientation = (int) (Math.atan2(l, j1) * 325.94900000000001D) & 0x7ff;
			}
		}
		if (class50_sub1_sub4_sub3.anInt1609 >= 32768) {
			int i = class50_sub1_sub4_sub3.anInt1609 - 32768;
			if (i == thisPlayerServerId)
				i = thisPlayerId;
			Player class50_sub1_sub4_sub3_sub2 = players[i];
			if (class50_sub1_sub4_sub3_sub2 != null) {
				int k1 = class50_sub1_sub4_sub3.worldX - class50_sub1_sub4_sub3_sub2.worldX;
				int l1 = class50_sub1_sub4_sub3.worldY - class50_sub1_sub4_sub3_sub2.worldY;
				if (k1 != 0 || l1 != 0)
					class50_sub1_sub4_sub3.nextStepOrientation = (int) (Math.atan2(k1, l1) * 325.94900000000001D) & 0x7ff;
			}
		}
		if ((class50_sub1_sub4_sub3.anInt1598 != 0 || class50_sub1_sub4_sub3.anInt1599 != 0)
				&& (class50_sub1_sub4_sub3.pathLength == 0 || class50_sub1_sub4_sub3.anInt1623 > 0)) {
			int j = class50_sub1_sub4_sub3.worldX - (class50_sub1_sub4_sub3.anInt1598 - nextTopLeftTileX - nextTopLeftTileX) * 64;
			int i1 = class50_sub1_sub4_sub3.worldY - (class50_sub1_sub4_sub3.anInt1599 - nextTopRightTileY - nextTopRightTileY) * 64;
			if (j != 0 || i1 != 0)
				class50_sub1_sub4_sub3.nextStepOrientation = (int) (Math.atan2(j, i1) * 325.94900000000001D) & 0x7ff;
			class50_sub1_sub4_sub3.anInt1598 = 0;
			class50_sub1_sub4_sub3.anInt1599 = 0;
		}
		int k = class50_sub1_sub4_sub3.nextStepOrientation - class50_sub1_sub4_sub3.anInt1612 & 0x7ff;
		if (k != 0) {
			if (k < class50_sub1_sub4_sub3.anInt1600 || k > 2048 - class50_sub1_sub4_sub3.anInt1600)
				class50_sub1_sub4_sub3.anInt1612 = class50_sub1_sub4_sub3.nextStepOrientation;
			else if (k > 1024)
				class50_sub1_sub4_sub3.anInt1612 -= class50_sub1_sub4_sub3.anInt1600;
			else
				class50_sub1_sub4_sub3.anInt1612 += class50_sub1_sub4_sub3.anInt1600;
			class50_sub1_sub4_sub3.anInt1612 &= 0x7ff;
			if (class50_sub1_sub4_sub3.movementAnimation == class50_sub1_sub4_sub3.idleAnimation
					&& class50_sub1_sub4_sub3.anInt1612 != class50_sub1_sub4_sub3.nextStepOrientation) {
				if (class50_sub1_sub4_sub3.standTurnAnimationId != -1) {
					class50_sub1_sub4_sub3.movementAnimation = class50_sub1_sub4_sub3.standTurnAnimationId;
					return;
				}
				class50_sub1_sub4_sub3.movementAnimation = class50_sub1_sub4_sub3.walkAnimationId;
			}
		}
	}

	public void method73(Actor class50_sub1_sub4_sub3, int i) {
		while (i >= 0)
			anInt1328 = incomingRandom.nextInt();
		class50_sub1_sub4_sub3.aBoolean1592 = false;
		if (class50_sub1_sub4_sub3.movementAnimation != -1) {
			AnimationSequence class14 = AnimationSequence.animations[class50_sub1_sub4_sub3.movementAnimation];
			class50_sub1_sub4_sub3.anInt1590++;
			if (class50_sub1_sub4_sub3.displayedMovementFrames < class14.frameCount
					&& class50_sub1_sub4_sub3.anInt1590 > class14.getFrameLength(class50_sub1_sub4_sub3.displayedMovementFrames)) {
				class50_sub1_sub4_sub3.anInt1590 = 1;
				class50_sub1_sub4_sub3.displayedMovementFrames++;
			}
			if (class50_sub1_sub4_sub3.displayedMovementFrames >= class14.frameCount) {
				class50_sub1_sub4_sub3.anInt1590 = 1;
				class50_sub1_sub4_sub3.displayedMovementFrames = 0;
			}
		}
		if (class50_sub1_sub4_sub3.graphic != -1 && pulseCycle >= class50_sub1_sub4_sub3.anInt1617) {
			if (class50_sub1_sub4_sub3.currentAnimation < 0)
				class50_sub1_sub4_sub3.currentAnimation = 0;
			AnimationSequence class14_1 = SpotAnimation.cache[class50_sub1_sub4_sub3.graphic].sequences;
			class50_sub1_sub4_sub3.anInt1616++;
			if (class50_sub1_sub4_sub3.currentAnimation < class14_1.frameCount
					&& class50_sub1_sub4_sub3.anInt1616 > class14_1.getFrameLength(class50_sub1_sub4_sub3.currentAnimation)) {
				class50_sub1_sub4_sub3.anInt1616 = 1;
				class50_sub1_sub4_sub3.currentAnimation++;
			}
			if (class50_sub1_sub4_sub3.currentAnimation >= class14_1.frameCount
					&& (class50_sub1_sub4_sub3.currentAnimation < 0 || class50_sub1_sub4_sub3.currentAnimation >= class14_1.frameCount))
				class50_sub1_sub4_sub3.graphic = -1;
		}
		if (class50_sub1_sub4_sub3.emoteAnimation != -1 && class50_sub1_sub4_sub3.animationDelay <= 1) {
			AnimationSequence class14_2 = AnimationSequence.animations[class50_sub1_sub4_sub3.emoteAnimation];
			if (class14_2.anInt305 == 1 && class50_sub1_sub4_sub3.anInt1613 > 0
					&& class50_sub1_sub4_sub3.anInt1606 <= pulseCycle && class50_sub1_sub4_sub3.anInt1607 < pulseCycle) {
				class50_sub1_sub4_sub3.animationDelay = 1;
				return;
			}
		}
		if (class50_sub1_sub4_sub3.emoteAnimation != -1 && class50_sub1_sub4_sub3.animationDelay == 0) {
			AnimationSequence class14_3 = AnimationSequence.animations[class50_sub1_sub4_sub3.emoteAnimation];
			class50_sub1_sub4_sub3.anInt1626++;
			if (class50_sub1_sub4_sub3.displayedEmoteFrames < class14_3.frameCount
					&& class50_sub1_sub4_sub3.anInt1626 > class14_3.getFrameLength(class50_sub1_sub4_sub3.displayedEmoteFrames)) {
				class50_sub1_sub4_sub3.anInt1626 = 1;
				class50_sub1_sub4_sub3.displayedEmoteFrames++;
			}
			if (class50_sub1_sub4_sub3.displayedEmoteFrames >= class14_3.frameCount) {
				class50_sub1_sub4_sub3.displayedEmoteFrames -= class14_3.frameStep;
				class50_sub1_sub4_sub3.anInt1628++;
				if (class50_sub1_sub4_sub3.anInt1628 >= class14_3.anInt304)
					class50_sub1_sub4_sub3.emoteAnimation = -1;
				if (class50_sub1_sub4_sub3.displayedEmoteFrames < 0 || class50_sub1_sub4_sub3.displayedEmoteFrames >= class14_3.frameCount)
					class50_sub1_sub4_sub3.emoteAnimation = -1;
			}
			class50_sub1_sub4_sub3.aBoolean1592 = class14_3.aBoolean300;
		}
		if (class50_sub1_sub4_sub3.animationDelay > 0)
			class50_sub1_sub4_sub3.animationDelay--;
	}

	public void method74(int i) {
		if (anInt1053 != -1 && (loadingStage == 2 || super.imageProducer != null)) {
			if (loadingStage == 2) {
				method88(tickDelta, anInt1053, (byte) 5);
				if (anInt960 != -1)
					method88(tickDelta, anInt960, (byte) 5);
				tickDelta = 0;
				method147(anInt1140);
				super.imageProducer.createRasterizer();
				Rasterizer3D.lineOffsets = anIntArray1003;
				Rasterizer.resetPixels();
				aBoolean1046 = true;
				Widget class13 = Widget.forId(anInt1053);
				if (class13.width == 512 && class13.height == 334 && class13.type == 0) {
					class13.width = 765;
					class13.height = 503;
				}
				method142(0, 0, class13, 0, 8);
				if (anInt960 != -1) {
					Widget class13_1 = Widget.forId(anInt960);
					if (class13_1.width == 512 && class13_1.height == 334 && class13_1.type == 0) {
						class13_1.width = 765;
						class13_1.height = 503;
					}
					method142(0, 0, class13_1, 0, 8);
				}
				if (!menuOpen) {
					processRightClick(-521);
					drawMenuTooltip();
				} else {
					method128(false);
				}
			}
			super.imageProducer.drawGraphics(0, 0, super.gameGraphics);
			return;
		}
		if (aBoolean1046) {
			method122(-906);
			aBoolean1046 = false;
			aClass18_906.drawGraphics(0, 4, super.gameGraphics);
			aClass18_907.drawGraphics(0, 357, super.gameGraphics);
			aClass18_908.drawGraphics(722, 4, super.gameGraphics);
			aClass18_909.drawGraphics(743, 205, super.gameGraphics);
			aClass18_910.drawGraphics(0, 0, super.gameGraphics);
			aClass18_911.drawGraphics(516, 4, super.gameGraphics);
			aClass18_912.drawGraphics(516, 205, super.gameGraphics);
			aClass18_913.drawGraphics(496, 357, super.gameGraphics);
			aClass18_914.drawGraphics(0, 338, super.gameGraphics);
			redrawTabArea = true;
			redrawChatbox = true;
			aBoolean950 = true;
			aBoolean1212 = true;
			if (loadingStage != 2) {
				aClass18_1158.drawGraphics(4, 4, super.gameGraphics);
				aClass18_1157.drawGraphics(550, 4, super.gameGraphics);
			}
			anInt1237++;
			if (anInt1237 > 85) {
				anInt1237 = 0;
				outBuffer.putOpcode(168);
			}
		}
		if (loadingStage == 2)
			method151(2);
		if (menuOpen && anInt1304 == 1)
			redrawTabArea = true;
		if (anInt1089 != -1) {
			boolean flag = method88(tickDelta, anInt1089, (byte) 5);
			if (flag)
				redrawTabArea = true;
		}
		if (atInventoryInterfaceType == 2)
			redrawTabArea = true;
		if (activeInterfaceType == 2)
			redrawTabArea = true;
		if (redrawTabArea) {
			method134((byte) 7);
			redrawTabArea = false;
		}
		if (backDialogueId == -1 && inputType == 0) {
			aClass13_1249.anInt231 = anInt1107 - anInt851 - 77;
			if (super.mouseX > 448 && super.mouseX < 560 && super.mouseY > 332)
				method42(anInt1107, 0, aClass13_1249, (byte) 102, super.mouseY - 357, -1, super.mouseX - 17, 77, 463);
			int j = anInt1107 - 77 - aClass13_1249.anInt231;
			if (j < 0)
				j = 0;
			if (j > anInt1107 - 77)
				j = anInt1107 - 77;
			if (anInt851 != j) {
				anInt851 = j;
				redrawChatbox = true;
			}
		}
		if (backDialogueId == -1 && inputType == 3) {
			int k = anInt862 * 14 + 7;
			aClass13_1249.anInt231 = anInt865;
			if (super.mouseX > 448 && super.mouseX < 560 && super.mouseY > 332)
				method42(k, 0, aClass13_1249, (byte) 102, super.mouseY - 357, -1, super.mouseX - 17, 77, 463);
			int i1 = aClass13_1249.anInt231;
			if (i1 < 0)
				i1 = 0;
			if (i1 > k - 77)
				i1 = k - 77;
			if (anInt865 != i1) {
				anInt865 = i1;
				redrawChatbox = true;
			}
		}
		if (backDialogueId != -1) {
			boolean flag1 = method88(tickDelta, backDialogueId, (byte) 5);
			if (flag1)
				redrawChatbox = true;
		}
		if (atInventoryInterfaceType == 3)
			redrawChatbox = true;
		if (activeInterfaceType == 3)
			redrawChatbox = true;
		if (clickToContinueString != null)
			redrawChatbox = true;
		if (menuOpen && anInt1304 == 2)
			redrawChatbox = true;
		if (redrawChatbox) {
			renderChatbox();
			redrawChatbox = false;
		}
		if (loadingStage == 2) {
			renderMinimap();
			aClass18_1157.drawGraphics(550, 4, super.gameGraphics);
		}
		if (anInt1213 != -1)
			aBoolean950 = true;
		if (aBoolean950) {
			if (anInt1213 != -1 && anInt1213 == anInt1285) {
				anInt1213 = -1;
				outBuffer.putOpcode(119);
				outBuffer.putByte(anInt1285);
			}
			aBoolean950 = false;
			aClass18_1110.createRasterizer();
			anIndexedImage1054.drawImage(0, 0);
			if (anInt1089 == -1) {
				if (anIntArray1081[anInt1285] != -1) {
					if (anInt1285 == 0)
						aClass50_Sub1_Sub1_Sub3_880.drawImage(22, 10);
					if (anInt1285 == 1)
						aClass50_Sub1_Sub1_Sub3_881.drawImage(54, 8);
					if (anInt1285 == 2)
						aClass50_Sub1_Sub1_Sub3_881.drawImage(82, 8);
					if (anInt1285 == 3)
						aClass50_Sub1_Sub1_Sub3_882.drawImage(110, 8);
					if (anInt1285 == 4)
						aClass50_Sub1_Sub1_Sub3_884.drawImage(153, 8);
					if (anInt1285 == 5)
						aClass50_Sub1_Sub1_Sub3_884.drawImage(181, 8);
					if (anInt1285 == 6)
						aClass50_Sub1_Sub1_Sub3_883.drawImage(209, 9);
				}
				if (anIntArray1081[0] != -1 && (anInt1213 != 0 || pulseCycle % 20 < 10))
					tabIcon[0].drawImage(29, 13);
				if (anIntArray1081[1] != -1 && (anInt1213 != 1 || pulseCycle % 20 < 10))
					tabIcon[1].drawImage(53, 11);
				if (anIntArray1081[2] != -1 && (anInt1213 != 2 || pulseCycle % 20 < 10))
					tabIcon[2].drawImage(82, 11);
				if (anIntArray1081[3] != -1 && (anInt1213 != 3 || pulseCycle % 20 < 10))
					tabIcon[3].drawImage(115, 12);
				if (anIntArray1081[4] != -1 && (anInt1213 != 4 || pulseCycle % 20 < 10))
					tabIcon[4].drawImage(153, 13);
				if (anIntArray1081[5] != -1 && (anInt1213 != 5 || pulseCycle % 20 < 10))
					tabIcon[5].drawImage(180, 11);
				if (anIntArray1081[6] != -1 && (anInt1213 != 6 || pulseCycle % 20 < 10))
					tabIcon[6].drawImage(208, 13);
			}
			aClass18_1110.drawGraphics(516, 160, super.gameGraphics);
			aClass18_1109.createRasterizer();
			anIndexedImage1053.drawImage(0, 0);
			if (anInt1089 == -1) {
				if (anIntArray1081[anInt1285] != -1) {
					if (anInt1285 == 7)
						aClass50_Sub1_Sub1_Sub3_983.drawImage(42, 0);
					if (anInt1285 == 8)
						aClass50_Sub1_Sub1_Sub3_984.drawImage(74, 0);
					if (anInt1285 == 9)
						aClass50_Sub1_Sub1_Sub3_984.drawImage(102, 0);
					if (anInt1285 == 10)
						aClass50_Sub1_Sub1_Sub3_985.drawImage(130, 1);
					if (anInt1285 == 11)
						aClass50_Sub1_Sub1_Sub3_987.drawImage(173, 0);
					if (anInt1285 == 12)
						aClass50_Sub1_Sub1_Sub3_987.drawImage(201, 0);
					if (anInt1285 == 13)
						aClass50_Sub1_Sub1_Sub3_986.drawImage(229, 0);
				}
				if (anIntArray1081[8] != -1 && (anInt1213 != 8 || pulseCycle % 20 < 10))
					tabIcon[7].drawImage(74, 2);
				if (anIntArray1081[9] != -1 && (anInt1213 != 9 || pulseCycle % 20 < 10))
					tabIcon[8].drawImage(102, 3);
				if (anIntArray1081[10] != -1 && (anInt1213 != 10 || pulseCycle % 20 < 10))
					tabIcon[9].drawImage(137, 4);
				if (anIntArray1081[11] != -1 && (anInt1213 != 11 || pulseCycle % 20 < 10))
					tabIcon[10].drawImage(174, 2);
				if (anIntArray1081[12] != -1 && (anInt1213 != 12 || pulseCycle % 20 < 10))
					tabIcon[11].drawImage(201, 2);
				if (anIntArray1081[13] != -1 && (anInt1213 != 13 || pulseCycle % 20 < 10))
					tabIcon[12].drawImage(226, 2);
			}
			aClass18_1109.drawGraphics(496, 466, super.gameGraphics);
			aClass18_1158.createRasterizer();
			Rasterizer3D.lineOffsets = anIntArray1002;
		}
		if (aBoolean1212) {
			aBoolean1212 = false;
			aClass18_1108.createRasterizer();
			anIndexedImage1052.drawImage(0, 0);
			fontNormal.drawStringCenter("Public chat", 55, 28, 0xffffff, true);
			if (publicChatMode == 0)
				fontNormal.drawStringCenter("On", 55, 41, 65280, true);
			if (publicChatMode == 1)
				fontNormal.drawStringCenter("Friends", 55, 41, 0xffff00, true);
			if (publicChatMode == 2)
				fontNormal.drawStringCenter("Off", 55, 41, 0xff0000, true);
			if (publicChatMode == 3)
				fontNormal.drawStringCenter("Hide", 55, 41, 65535, true);
			fontNormal.drawStringCenter("Private chat", 184, 28, 0xffffff, true);
			if (privateChatMode == 0)
				fontNormal.drawStringCenter("On", 184, 41, 65280, true);
			if (privateChatMode == 1)
				fontNormal.drawStringCenter("Friends", 184, 41, 0xffff00, true);
			if (privateChatMode == 2)
				fontNormal.drawStringCenter("Off", 184, 41, 0xff0000, true);
			fontNormal.drawStringCenter("Trade/compete", 324, 28, 0xffffff, true);
			if (tradeMode == 0)
				fontNormal.drawStringCenter("On", 324, 41, 65280, true);
			if (tradeMode == 1)
				fontNormal.drawStringCenter("Friends", 324, 41, 0xffff00, true);
			if (tradeMode == 2)
				fontNormal.drawStringCenter("Off", 324, 41, 0xff0000, true);
			fontNormal.drawStringCenter("Report abuse", 458, 33, 0xffffff, true);
			aClass18_1108.drawGraphics(0, 453, super.gameGraphics);
			aClass18_1158.createRasterizer();
			Rasterizer3D.lineOffsets = anIntArray1002;
		}
		tickDelta = 0;
		if (i != 7) {
			for (int l = 1; l > 0; l++);
		}
	}

	private void renderSplitPrivateMessages() {
		if (anInt1223 == 0)
			return;

		TypeFace typeFace = fontNormal;
		int line = 0;

		if (systemUpdateTime != 0)
			line = 1;

		for (int i = 0; i < 100; i++) {
            if (chatMessages[i] != null) {
                int type = chatTypes[i];
                String name = chatPlayerNames[i];
                byte privilege = 0;

                if (name != null && name.startsWith("@cr1@")) {
                    name = name.substring(5);
                    privilege = 1;
                }

                if (name != null && name.startsWith("@cr2@")) {
                    name = name.substring(5);
                    privilege = 2;
                }

                if ((type == 3 || type == 7) && (type == 7 || privateChatMode == 0 || privateChatMode == 1 && method148(13292, name))) {
                    int y = 329 - line * 13;
                    int x = 4;

                    typeFace.drawString("From", x, y, 0);
                    typeFace.drawString("From", x, y - 1, 65535);

                    x += typeFace.getStringEffectWidth("From ");

                    if (privilege == 1) {
                        moderatorIcon[0].drawImage(x, y - 12);
                        x += 14;
                    }

                    if (privilege == 2) {
                        moderatorIcon[1].drawImage(x, y - 12);
                        x += 14;
                    }

                    typeFace.drawString(name + ": " + chatMessages[i], x, y, 0);
                    typeFace.drawString(name + ": " + chatMessages[i], x, y - 1, 65535);

                    if (++line >= 5)
                        return;
                }

                if (type == 5 && privateChatMode < 2) {
                    int y = 329 - line * 13;

                    typeFace.drawString(chatMessages[i], 4, y, 0);
                    typeFace.drawString(chatMessages[i], 4, y - 1, 65535);

                    if (++line >= 5)
                        return;
                }

                if (type == 6 && privateChatMode < 2) {
                    int y = 329 - line * 13;

                    typeFace.drawString("To " + name + ": " + chatMessages[i], 4, y, 0);
                    typeFace.drawString("To " + name + ": " + chatMessages[i], 4, y - 1, 65535);

                    if (++line >= 5)
                        return;
                }
            }
        }
	}

	public void init() {
		world = Integer.parseInt(getParameter("nodeid"));
		portOffset = Integer.parseInt(getParameter("portoff"));
		String s = getParameter("lowmem");
		if (s != null && s.equals("1"))
			setLowMemory();
		else
			setHighMemory();
		String s1 = getParameter("free");
		if (s1 != null && s1.equals("1"))
			memberServer = false;
		else
			memberServer = true;
		initializeApplet(765, 503);
	}

	public void method76(int i) {
		while (i >= 0)
			groundItems = null;
		for (GameAnimableObject gameAnimableObject = (GameAnimableObject) aClass6_1210.first(); gameAnimableObject != null; gameAnimableObject = (GameAnimableObject) aClass6_1210
				.next())
			if (gameAnimableObject.plane != plane || gameAnimableObject.transformCompleted)
				gameAnimableObject.remove();
			else if (pulseCycle >= gameAnimableObject.loopCycle) {
				gameAnimableObject.nextFrame(tickDelta);
				if (gameAnimableObject.transformCompleted)
					gameAnimableObject.remove();
				else
					currentScene.addEntity(-1, gameAnimableObject, gameAnimableObject.x,
							gameAnimableObject.z, false, 0, gameAnimableObject.plane, 60,
							gameAnimableObject.y, 0);
			}

	}

	public void method77(boolean flag) {
		if (flag)
			opcode = -1;
		do {
			OnDemandNode class50_sub1_sub3;
			do {
				class50_sub1_sub3 = onDemandRequester.next();
				if (class50_sub1_sub3 == null)
					return;
				if (class50_sub1_sub3.type == 0) {
					Model.loadModelHeader(class50_sub1_sub3.buffer, class50_sub1_sub3.id);
					if ((onDemandRequester.modelId(class50_sub1_sub3.id) & 0x62) != 0) {
						redrawTabArea = true;
						if (backDialogueId != -1 || dialogueId != -1)
							redrawChatbox = true;
					}
				}
				if (class50_sub1_sub3.type == 1 && class50_sub1_sub3.buffer != null)
					Animation.method236(class50_sub1_sub3.buffer);
				if (class50_sub1_sub3.type == 2 && class50_sub1_sub3.id == nextSong && class50_sub1_sub3.buffer != null)
					method24(songChanging, class50_sub1_sub3.buffer, 659);
				if (class50_sub1_sub3.type == 3 && loadingStage == 1) {
					for (int i = 0; i < aByteArrayArray838.length; i++) {
						if (anIntArray857[i] == class50_sub1_sub3.id) {
							aByteArrayArray838[i] = class50_sub1_sub3.buffer;
							if (class50_sub1_sub3.buffer == null)
								anIntArray857[i] = -1;
							break;
						}
						if (anIntArray858[i] != class50_sub1_sub3.id)
							continue;
						aByteArrayArray1232[i] = class50_sub1_sub3.buffer;
						if (class50_sub1_sub3.buffer == null)
							anIntArray858[i] = -1;
						break;
					}

				}
			} while (class50_sub1_sub3.type != 93 || !onDemandRequester.method334(class50_sub1_sub3.id, false));
			Region.passiveRequestGameObjectModels(onDemandRequester, new Buffer(class50_sub1_sub3.buffer));
		} while (true);
	}

	private void login(String username, String password, boolean reconnecting) {
		SignLink.errorName = username;

		try {
			if (!reconnecting) {
				statusLineOne = "";
				statusLineTwo = "Connecting to server...";

				drawLoginScreen(true);
			}

			gameConnection = new BufferedConnection(this, openSocket(Configuration.GAME_PORT + portOffset));
			long base37name = TextUtils.nameToLong(username);
			int hash = (int) (base37name >> 16 & 31L);
			outBuffer.currentPosition = 0;

			outBuffer.putByte(14);
			outBuffer.putByte(hash);
			gameConnection.write(2, 0, outBuffer.buffer);

			for (int j = 0; j < 8; j++)
				gameConnection.read();

			int responseCode = gameConnection.read();
			int initialResponseCode = responseCode;

			if (responseCode == 0) {
				gameConnection.read(buffer.buffer, 0, 8);

				buffer.currentPosition = 0;
				serverSeed = buffer.getLong();
				int seed[] = new int[4];

				seed[0] = (int) (Math.random() * 99999999D);
				seed[1] = (int) (Math.random() * 99999999D);
				seed[2] = (int) (serverSeed >> 32);
				seed[3] = (int) serverSeed;

				outBuffer.currentPosition = 0;

				outBuffer.putByte(10);
				outBuffer.putInt(seed[0]);
				outBuffer.putInt(seed[1]);
				outBuffer.putInt(seed[2]);
				outBuffer.putInt(seed[3]);
				outBuffer.putInt(SignLink.uid);
				outBuffer.putString(username);
				outBuffer.putString(password);

				if (Configuration.RSA_ENABLED)
				    outBuffer.encrypt(Configuration.RSA_MODULUS, Configuration.RSA_PUBLIC_KEY);

				tempBuffer.currentPosition = 0;

				if (reconnecting)
					tempBuffer.putByte(18);
				else
					tempBuffer.putByte(16);

				tempBuffer.putByte(outBuffer.currentPosition + 36 + 1 + 1 + 2);
				tempBuffer.putByte(255);
				tempBuffer.putShort(SignLink.CLIENT_REVISION);
				tempBuffer.putByte(lowMemory ? 1 : 0);

				for (int i = 0; i < 9; i++)
					tempBuffer.putInt(archiveHashes[i]);

				tempBuffer.putBytes(outBuffer.buffer, 0, outBuffer.currentPosition);

				outBuffer.random = new ISAACCipher(seed);

				for (int i = 0; i < 4; i++)
					seed[i] += 50;

				incomingRandom = new ISAACCipher(seed);

				gameConnection.write(tempBuffer.currentPosition, 0, tempBuffer.buffer);

				responseCode = gameConnection.read();
			}

			if (responseCode == 1) {
				try {
					Thread.sleep(2000L);
				} catch (Exception ignored) {}

				login(username, password, reconnecting);
				return;
			}

			if (responseCode == 2) {
				playerRights = gameConnection.read();
				accountFlagged = gameConnection.read() == 1;
				aLong902 = 0L;
				duplicateClickCount = 0;
				mouseCapturer.coord = 0;
				super.awtFocus = true;
				aBoolean1275 = true;
				loggedIn = true;
				outBuffer.currentPosition = 0;
				buffer.currentPosition = 0;
				opcode = -1;
				lastOpcode = -1;
				secondLastOpcode = -1;
				thirdLastOpcode = -1;
				packetSize = 0;
				timeoutCounter = 0;
				systemUpdateTime = 0;
				anInt873 = 0;
				anInt1197 = 0;
				menuActionRow = 0;
				menuOpen = false;
				super.idleTime = 0;

				for (int j1 = 0; j1 < 100; j1++)
					chatMessages[j1] = null;

				itemSelected = 0;
				widgetSelected = 0;
				loadingStage = 0;
				currentSound = 0;
				anInt853 = (int) (Math.random() * 100D) - 50;
				anInt1009 = (int) (Math.random() * 110D) - 55;
				anInt1255 = (int) (Math.random() * 80D) - 40;
				anInt916 = (int) (Math.random() * 120D) - 60;
				anInt1233 = (int) (Math.random() * 30D) - 20;
				cameraHorizontal = (int) (Math.random() * 20D) - 10 & 0x7ff;
				minimapState = 0;
				anInt1276 = -1;
				destinationX = 0;
				destinationY = 0;
				localPlayerCount = 0;
				anInt1133 = 0;

				for (int i2 = 0; i2 < anInt968; i2++) {
					players[i2] = null;
					cachedAppearances[i2] = null;
				}

				for (int k2 = 0; k2 < 16384; k2++)
					npcs[k2] = null;

				localPlayer = players[thisPlayerId] = new Player();

				aClass6_1282.getNodeCount();
				aClass6_1210.getNodeCount();

				for (int l2 = 0; l2 < 4; l2++) {
					for (int i3 = 0; i3 < 104; i3++) {
						for (int k3 = 0; k3 < 104; k3++)
							groundItems[l2][i3][k3] = null;
					}
				}

				aClass6_1261 = new LinkedList();
				friendListStatus = 0;
				friendsCount = 0;

				method44(aBoolean1190, dialogueId);
				dialogueId = -1;

				method44(aBoolean1190, backDialogueId);
				backDialogueId = -1;

				method44(aBoolean1190, openInterfaceId);
				openInterfaceId = -1;

				method44(aBoolean1190, anInt1053);
				anInt1053 = -1;

				method44(aBoolean1190, anInt960);
				anInt960 = -1;

				method44(aBoolean1190, anInt1089);
				anInt1089 = -1;

				method44(aBoolean1190, anInt1279);
				anInt1279 = -1;

				aBoolean1239 = false;
				anInt1285 = 3;
				inputType = 0;
				menuOpen = false;
				messagePromptRaised = false;
				clickToContinueString = null;
				anInt1319 = 0;
				anInt1213 = -1;
				characterEditChangeGenger = true;

				method25();

				for (int j3 = 0; j3 < 5; j3++)
					characterEditColors[j3] = 0;

				for (int l3 = 0; l3 < 5; l3++) {
					aStringArray1069[l3] = null;
					aBooleanArray1070[l3] = false;
				}

				anInt1100 = 0;
				anInt1165 = 0;
				anInt1235 = 0;
				anInt1052 = 0;
				anInt1139 = 0;
				anInt841 = 0;
				anInt1230 = 0;
				anInt1013 = 0;
				anInt1049 = 0;
				anInt1162 = 0;

				method122(-906);
				return;
			}

			if (responseCode == 3) {
				statusLineOne = "";
				statusLineTwo = "Invalid username or password.";
				return;
			}

			if (responseCode == 4) {
				statusLineOne = "Your account has been disabled.";
				statusLineTwo = "Please check your message-centre for details.";
				return;
			}

			if (responseCode == 5) {
				statusLineOne = "Your account is already logged in.";
				statusLineTwo = "Try again in 60 secs...";
				return;
			}

			if (responseCode == 6) {
				statusLineOne = "RuneScape has been updated!";
				statusLineTwo = "Please reload this page.";
				return;
			}

			if (responseCode == 7) {
				statusLineOne = "This world is full.";
				statusLineTwo = "Please use a different world.";
				return;
			}

			if (responseCode == 8) {
				statusLineOne = "Unable to connect.";
				statusLineTwo = "Login server offline.";
				return;
			}

			if (responseCode == 9) {
				statusLineOne = "Login limit exceeded.";
				statusLineTwo = "Too many connections from your address.";
				return;
			}

			if (responseCode == 10) {
				statusLineOne = "Unable to connect.";
				statusLineTwo = "Bad session id.";
				return;
			}

			if (responseCode == 12) {
				statusLineOne = "You need a members account to login to this world.";
				statusLineTwo = "Please subscribe, or use a different world.";
				return;
			}

			if (responseCode == 13) {
				statusLineOne = "Could not complete login.";
				statusLineTwo = "Please try using a different world.";
				return;
			}

			if (responseCode == 14) {
				statusLineOne = "The server is being updated.";
				statusLineTwo = "Please wait 1 minute and try again.";
				return;
			}

			if (responseCode == 15) {
				loggedIn = true;
				outBuffer.currentPosition = 0;
				buffer.currentPosition = 0;
				opcode = -1;
				lastOpcode = -1;
				secondLastOpcode = -1;
				thirdLastOpcode = -1;
				packetSize = 0;
				timeoutCounter = 0;
				systemUpdateTime = 0;
				menuActionRow = 0;
				menuOpen = false;
				aLong1229 = System.currentTimeMillis();
				return;
			}

			if (responseCode == 16) {
				statusLineOne = "Login attempts exceeded.";
				statusLineTwo = "Please wait 1 minute and try again.";
				return;
			}

			if (responseCode == 17) {
				statusLineOne = "You are standing in a members-only area.";
				statusLineTwo = "To play on this world move to a free area first";
				return;
			}

			if (responseCode == 18) {
				statusLineOne = "Account locked as we suspect it has been stolen.";
				statusLineTwo = "Press 'recover a locked account' on front page.";
				return;
			}

			if (responseCode == 20) {
				statusLineOne = "Invalid loginserver requested";
				statusLineTwo = "Please try using a different world.";
				return;
			}

			if (responseCode == 21) {
				int time = gameConnection.read();

				for (time += 3; time >= 0; time--) {
					statusLineOne = "You have only just left another world";
					statusLineTwo = "Your profile will be transferred in: " + time;

					drawLoginScreen(true);

					try {
						Thread.sleep(1200L);
					} catch (Exception ignored) {}
				}

				login(username, password, reconnecting);
				return;
			}

			if (responseCode == 22) {
				statusLineOne = "Malformed login packet.";
				statusLineTwo = "Please try again.";
				return;
			}

			if (responseCode == 23) {
				statusLineOne = "No reply from loginserver.";
				statusLineTwo = "Please try again.";
				return;
			}

			if (responseCode == 24) {
				statusLineOne = "Error loading your profile.";
				statusLineTwo = "Please contact customer support.";
				return;
			}

			if (responseCode == 25) {
				statusLineOne = "Unexpected loginserver response.";
				statusLineTwo = "Please try using a different world.";
				return;
			}

			if (responseCode == 26) {
				statusLineOne = "This computers address has been blocked";
				statusLineTwo = "as it was used to break our rules";
				return;
			}

			if (responseCode == -1) {
				if (initialResponseCode == 0) {
					if (anInt850 < 2) {
						try {
							Thread.sleep(2000L);
						} catch (Exception ignored) {}

						anInt850++;

						login(username, password, reconnecting);
						return;
					} else {
						statusLineOne = "No response from loginserver";
						statusLineTwo = "Please wait 1 minute and try again.";
						return;
					}
				} else {
					statusLineOne = "No response from server";
					statusLineTwo = "Please try using a different world.";
					return;
				}
			} else {
				System.out.println("response:" + responseCode);

				statusLineOne = "Unexpected server response";
				statusLineTwo = "Please try using a different world.";
				return;
			}
		} catch (IOException ex) {
			statusLineOne = "";
		}

		statusLineTwo = "Error connecting to server.";
	}

	public boolean method80(int dstY, int j, int dstX, int l) {
		int i1 = l >> 14 & 0x7fff;
		int j1 = currentScene.method271(plane, dstX, dstY, l);
		if (j1 == -1)
			return false;
		int objectType = j1 & 0x1f;
		int l1 = j1 >> 6 & 3;
		if (objectType == 10 || objectType == 11 || objectType == 22) {
			GameObjectDefinition class47 = GameObjectDefinition.getDefinition(i1);
			int i2;
			int j2;
			if (l1 == 0 || l1 == 2) {
				i2 = class47.sizeX;
				j2 = class47.sizeY;
			} else {
				i2 = class47.sizeY;
				j2 = class47.sizeX;
			}
			int k2 = class47.anInt764;
			if (l1 != 0)
				k2 = (k2 << l1 & 0xf) + (k2 >> 4 - l1);
			walk(true, false, dstY, localPlayer.pathY[0], i2, j2, 2, 0, dstX, k2, 0,
					localPlayer.pathX[0]);
		} else {
			walk(true, false, dstY, localPlayer.pathY[0], 0, 0, 2, objectType + 1, dstX, 0, l1,
					localPlayer.pathX[0]);
		}
		anInt1020 = super.clickX;
		anInt1021 = super.clickY;
		crossType = 2;
		crossIndex = 0;
		packetSize += j;
		return true;
	}

	private void calculateFlamePositions() { //TODO: Needs more refactoring
		int c = 256;

		for (int x = 10; x < 117; x++) {
			int rand = (int) (Math.random() * 100D);

			if (rand < 50)
				anIntArray1084[x + (c - 2 << 7)] = 255;
		}

		for (int i = 0; i < 100; i++) {
            int x = (int) (Math.random() * 124D) + 2;
            int y = (int) (Math.random() * 128D) + 128;
			int pixel = x + (y << 7);
			anIntArray1084[pixel] = 192;
		}

		for (int y = 1; y < c - 1; y++) {
			for (int x = 1; x < 127; x++) {
				int pixel = x + (y << 7);
				anIntArray1085[pixel] = (anIntArray1084[pixel - 1] + anIntArray1084[pixel + 1] + anIntArray1084[pixel - 128] + anIntArray1084[pixel + 128]) / 4;
			}
		}

		anInt1238 += 128;

		if (anInt1238 > anIntArray1176.length) {
			anInt1238 -= anIntArray1176.length;
			int rand = (int) (Math.random() * 12D);

			method83(titleFlameEmblem[rand], 0);
		}

		for (int y = 1; y < c - 1; y++) {
			for (int x = 1; x < 127; x++) {
				int pixel = x + (y << 7);
				int i4 = anIntArray1085[pixel + 128] - anIntArray1176[pixel + anInt1238 & anIntArray1176.length - 1] / 5;

				if (i4 < 0)
					i4 = 0;

				anIntArray1084[pixel] = i4;
			}
		}

		for (int i = 0; i < c - 1; i++)
			anIntArray1166[i] = anIntArray1166[i + 1];

		anIntArray1166[c - 1] = (int) (Math.sin((double) pulseCycle / 14D) * 16D + Math.sin((double) pulseCycle / 15D)
				* 14D + Math.sin((double) pulseCycle / 16D) * 12D);

		if (anInt1047 > 0)
			anInt1047 -= 4;
		if (anInt1048 > 0)
			anInt1048 -= 4;
		if (anInt1047 == 0 && anInt1048 == 0) {
			int rand = (int) (Math.random() * 2000D);

			if (rand == 0)
				anInt1047 = 1024;
			if (rand == 1)
				anInt1048 = 1024;
		}
	}

	public void method82(ActorDefinition class37, int i, int j, int k, byte byte0) {
		if (byte0 != -76)
			groundItems = null;
		if (menuActionRow >= 400)
			return;
		if (class37.childrenIds != null)
			class37 = class37.getChildDefinition();
		if (class37 == null)
			return;
		if (!class37.clickable)
			return;
		String s = class37.name;
		if (class37.combatLevel != 0)
			s = s + getCombatLevelColour(localPlayer.combatLevel, class37.combatLevel) + " (level-" + class37.combatLevel + ")";
		if (itemSelected == 1) {
			menuActionTexts[menuActionRow] = "Use " + aString1150 + " with @yel@" + s;
			menuActionTypes[menuActionRow] = 347;
			selectedMenuActions[menuActionRow] = k;
			firstMenuOperand[menuActionRow] = j;
			secondMenuOperand[menuActionRow] = i;
			menuActionRow++;
			return;
		}
		if (widgetSelected == 1) {
			if ((anInt1173 & 2) == 2) {
				menuActionTexts[menuActionRow] = selectedWidgetName + " @yel@" + s;
				menuActionTypes[menuActionRow] = 67;
				selectedMenuActions[menuActionRow] = k;
				firstMenuOperand[menuActionRow] = j;
				secondMenuOperand[menuActionRow] = i;
				menuActionRow++;
				return;
			}
		} else {
			if (class37.actions != null) {
				for (int l = 4; l >= 0; l--)
					if (class37.actions[l] != null && !class37.actions[l].equalsIgnoreCase("attack")) {
						menuActionTexts[menuActionRow] = class37.actions[l] + " @yel@" + s;
						if (l == 0)
							menuActionTypes[menuActionRow] = 318;
						if (l == 1)
							menuActionTypes[menuActionRow] = 921;
						if (l == 2)
							menuActionTypes[menuActionRow] = 118;
						if (l == 3)
							menuActionTypes[menuActionRow] = 553;
						if (l == 4)
							menuActionTypes[menuActionRow] = 432;
						selectedMenuActions[menuActionRow] = k;
						firstMenuOperand[menuActionRow] = j;
						secondMenuOperand[menuActionRow] = i;
						menuActionRow++;
					}

			}
			if (class37.actions != null) {
				for (int i1 = 4; i1 >= 0; i1--)
					if (class37.actions[i1] != null && class37.actions[i1].equalsIgnoreCase("attack")) {
						char c = '\0';
						if (class37.combatLevel > localPlayer.combatLevel)
							c = '\u07D0';
						menuActionTexts[menuActionRow] = class37.actions[i1] + " @yel@" + s;
						if (i1 == 0)
							menuActionTypes[menuActionRow] = 318 + c;
						if (i1 == 1)
							menuActionTypes[menuActionRow] = 921 + c;
						if (i1 == 2)
							menuActionTypes[menuActionRow] = 118 + c;
						if (i1 == 3)
							menuActionTypes[menuActionRow] = 553 + c;
						if (i1 == 4)
							menuActionTypes[menuActionRow] = 432 + c;
						selectedMenuActions[menuActionRow] = k;
						firstMenuOperand[menuActionRow] = j;
						secondMenuOperand[menuActionRow] = i;
						menuActionRow++;
					}

			}
			menuActionTexts[menuActionRow] = "Examine @yel@" + s;
			menuActionTypes[menuActionRow] = 1668;
			selectedMenuActions[menuActionRow] = k;
			firstMenuOperand[menuActionRow] = j;
			secondMenuOperand[menuActionRow] = i;
			menuActionRow++;
		}
	}

	public void method83(IndexedImage class50_sub1_sub1_sub3, int i) {
		packetSize += i;
		int j = 256;
		for (int k = 0; k < anIntArray1176.length; k++)
			anIntArray1176[k] = 0;

		for (int l = 0; l < 5000; l++) {
			int i1 = (int) (Math.random() * 128D * (double) j);
			anIntArray1176[i1] = (int) (Math.random() * 256D);
		}

		for (int j1 = 0; j1 < 20; j1++) {
			for (int k1 = 1; k1 < j - 1; k1++) {
				for (int i2 = 1; i2 < 127; i2++) {
					int k2 = i2 + (k1 << 7);
					anIntArray1177[k2] = (anIntArray1176[k2 - 1] + anIntArray1176[k2 + 1] + anIntArray1176[k2 - 128] + anIntArray1176[k2 + 128]) / 4;
				}

			}

			int ai[] = anIntArray1176;
			anIntArray1176 = anIntArray1177;
			anIntArray1177 = ai;
		}

		if (class50_sub1_sub1_sub3 != null) {
			int l1 = 0;
			for (int j2 = 0; j2 < class50_sub1_sub1_sub3.height; j2++) {
				for (int l2 = 0; l2 < class50_sub1_sub1_sub3.width; l2++)
					if (class50_sub1_sub1_sub3.pixels[l1++] != 0) {
						int i3 = l2 + 16 + class50_sub1_sub1_sub3.xDrawOffset;
						int j3 = j2 + 16 + class50_sub1_sub1_sub3.yDrawOffset;
						int k3 = i3 + (j3 << 7);
						anIntArray1176[k3] = 0;
					}

			}

		}
	}

	private void renderChatbox() {
		chatboxProducingGraphicsBuffer.createRasterizer();

		Rasterizer3D.lineOffsets = chatboxLineOffsets;

		chatboxBackgroundImage.drawImage(0, 0);

		if (messagePromptRaised) {
			fontBold.drawStringLeft(chatboxInputMessage, 239, 40, 0);
			fontBold.drawStringLeft(chatMessage + "*", 239, 60, 128);
		} else if (inputType == 1) {
			fontBold.drawStringLeft("Enter amount:", 239, 40, 0);
			fontBold.drawStringLeft(inputInputMessage + "*", 239, 60, 128);
		} else if (inputType == 2) {
			fontBold.drawStringLeft("Enter name:", 239, 40, 0);
			fontBold.drawStringLeft(inputInputMessage + "*", 239, 60, 128);
		} else if (inputType == 3) {
			if (!inputInputMessage.equals(aString861)) {
				method14(inputInputMessage, 2);
				aString861 = inputInputMessage;
			}

			TypeFace typeFace = fontNormal;

			Rasterizer.setCoordinates(0, 0, 77, 463);

			for (int i = 0; i < anInt862; i++) {
				int y = (18 + i * 14) - anInt865;

				if (y > 0 && y < 110)
					typeFace.drawStringLeft(aStringArray863[i], 239, y, 0);
			}

			Rasterizer.resetCoordinates();

			if (anInt862 > 5)
				method56(true, anInt865, 463, 77, anInt862 * 14 + 7, 0);

			if (inputInputMessage.length() == 0)
			    fontBold.drawStringLeft("Enter object name", 239, 40, 255);
			else if (anInt862 == 0)
				fontBold.drawStringLeft("No matching objects found, please shorten search", 239, 40, 0);

			typeFace.drawStringLeft(inputInputMessage + "*", 239, 90, 0);
			Rasterizer.drawHorizontalLine(0, 77, 479, 0);
		} else if (clickToContinueString != null) {
			fontBold.drawStringLeft(clickToContinueString, 239, 40, 0);
			fontBold.drawStringLeft("Click to continue", 239, 60, 128);
		} else if (backDialogueId != -1) {
            method142(0, 0, Widget.forId(backDialogueId), 0, 8);
        } else if (dialogueId != -1) {
			method142(0, 0, Widget.forId(dialogueId), 0, 8);
		} else {
			TypeFace typeFace = fontNormal;
			int line = 0;

			Rasterizer.setCoordinates(0, 0, 77, 463);

			for (int i = 0; i < 100; i++) {
                if (chatMessages[i] != null) {
                    String name = chatPlayerNames[i];
                    int type = chatTypes[i];
                    int y = (70 - line * 14) + anInt851;
                    byte privilege = 0;

                    if (name != null && name.startsWith("@cr1@")) {
                        name = name.substring(5);
                        privilege = 1;
                    }

                    if (name != null && name.startsWith("@cr2@")) {
                        name = name.substring(5);
                        privilege = 2;
                    }

                    if (type == 0) {
                        if (y > 0 && y < 110)
                            typeFace.drawString(chatMessages[i], 4, y, 0);

                        line++;
                    }

                    if ((type == 1 || type == 2) && (type == 1 || publicChatMode == 0 || publicChatMode == 1 && method148(13292, name))) {
                        if (y > 0 && y < 110) {
                            int x = 4;

                            if (privilege == 1) {
                                moderatorIcon[0].drawImage(x, y - 12);
                                x += 14;
                            }

                            if (privilege == 2) {
                                moderatorIcon[1].drawImage(x, y - 12);
                                x += 14;
                            }

                            typeFace.drawString(name + ":", x, y, 0);

                            x += typeFace.getStringEffectWidth(name) + 8;

                            typeFace.drawString(chatMessages[i], x, y, 255);
                        }

                        line++;
                    }

                    if ((type == 3 || type == 7) && anInt1223 == 0
                            && (type == 7 || privateChatMode == 0 || privateChatMode == 1 && method148(13292, name))) {
                        if (y > 0 && y < 110) {
                            int x = 4;

                            typeFace.drawString("From", x, y, 0);

                            x += typeFace.getStringEffectWidth("From ");

                            if (privilege == 1) {
                                moderatorIcon[0].drawImage(x, y - 12);
                                x += 14;
                            }

                            if (privilege == 2) {
                                moderatorIcon[1].drawImage(x, y - 12);
                                x += 14;
                            }

                            typeFace.drawString(name + ":", x, y, 0);

                            x += typeFace.getStringEffectWidth(name) + 8;

                            typeFace.drawString(chatMessages[i], x, y, 0x800000);
                        }

                        line++;
                    }

                    if (type == 4 && (tradeMode == 0 || tradeMode == 1 && method148(13292, name))) {
                        if (y > 0 && y < 110)
                            typeFace.drawString(name + " " + chatMessages[i], 4, y, 0x800080);

                        line++;
                    }

                    if (type == 5 && anInt1223 == 0 && privateChatMode < 2) {
                        if (y > 0 && y < 110)
                            typeFace.drawString(chatMessages[i], 4, y, 0x800000);

                        line++;
                    }

                    if (type == 6 && anInt1223 == 0 && privateChatMode < 2) {
                        if (y > 0 && y < 110) {
                            typeFace.drawString("To " + name + ":", 4, y, 0);
                            typeFace.drawString(chatMessages[i], 12 + typeFace.getStringEffectWidth("To " + name), y, 0x800000);
                        }

                        line++;
                    }

                    if (type == 8 && (tradeMode == 0 || tradeMode == 1 && method148(13292, name))) {
                        if (y > 0 && y < 110)
                            typeFace.drawString(name + " " + chatMessages[i], 4, y, 0x7e3200);

                        line++;
                    }
                }
            }

			Rasterizer.resetCoordinates();

			anInt1107 = line * 14 + 7;

			if (anInt1107 < 78)
				anInt1107 = 78;

			method56(true, anInt1107 - anInt851 - 77, 463, 77, anInt1107, 0);

			String name;

			if (localPlayer != null && localPlayer.playerName != null)
				name = localPlayer.playerName;
			else
				name = TextUtils.formatName(username);

			typeFace.drawString(name + ":", 4, 90, 0);
			typeFace.drawString(chatboxInput + "*", 6 + typeFace.getStringEffectWidth(name + ": "), 90, 255);
			Rasterizer.drawHorizontalLine(0, 77, 479, 0);
		}

		if (menuOpen && anInt1304 == 2)
			method128(false);

		chatboxProducingGraphicsBuffer.drawGraphics(17, 357, super.gameGraphics);
		aClass18_1158.createRasterizer();

		Rasterizer3D.lineOffsets = anIntArray1002;
	}

	private void processActorOverheadText() {
		for (int i = -1; i < localPlayerCount; i++) {
			int index = i == -1 ? thisPlayerId : playerList[i];
			Player player = players[index];

			if (player != null && player.textCycle > 0) {
				player.textCycle--;

				if (player.textCycle == 0)
					player.forcedChat = null;
			}
		}

		for (int i = 0; i < anInt1133; i++) {
			int index = anIntArray1134[i];
			Npc npc = npcs[index];

			if (npc != null && npc.textCycle > 0) {
				npc.textCycle--;

				if (npc.textCycle == 0)
					npc.forcedChat = null;
			}
		}
	}

	private void requestArchiveCrcs() {
		int reconnectionDelay = 5;
		int attempts = 0;
        archiveHashes[8] = 0;

		while (archiveHashes[8] == 0) {
			String error = "Unknown problem";

			drawLoadingText(20, "Connecting to web server");

			try {
				DataInputStream stream = openJaggrabStream("crc" + (int) (Math.random() * 99999999D) + "-" + 377);
				Buffer jaggrab = new Buffer(new byte[40]);

				stream.readFully(jaggrab.buffer, 0, 40);
				stream.close();

				for (int i = 0; i < 9; i++)
					archiveHashes[i] = jaggrab.getInt();

				int expectedCrc = jaggrab.getInt();
				int calculatedCrc = 1234;

				for (int i = 0; i < 9; i++)
					calculatedCrc = (calculatedCrc << 1) + archiveHashes[i];

				if (expectedCrc != calculatedCrc) {
					error = "Checksum problem";
					archiveHashes[8] = 0;
				}
			} catch (EOFException _ex) {
				error = "EOF problem";
				archiveHashes[8] = 0;
			} catch (IOException _ex) {
				error = "Connection problem";
				archiveHashes[8] = 0;
			} catch (Exception _ex) {
				error = "Logic problem";
				archiveHashes[8] = 0;

				if (!SignLink.reportError)
					return;
			}

			if (archiveHashes[8] == 0) {
				attempts++;

				for (int time = reconnectionDelay; time > 0; time--) {
					if (attempts >= 10) {
						drawLoadingText(10, "Game updated - please reload page");

						time = 10;
					} else {
						drawLoadingText(10, error + " - Will retry in " + time + " secs.");
					}

					try {
						Thread.sleep(1000L);
					} catch (Exception ignored) {}
				}

				reconnectionDelay *= 2;

				if (reconnectionDelay > 60)
					reconnectionDelay = 60;

				useJaggrab = !useJaggrab;
			}
		}
	}

	private void renderMinimap() {
		aClass18_1157.createRasterizer();

		if (minimapState == 2) {
			byte[] mmBackgroundPixels = minimapBackgroundImage.pixels;
			int[] rasterPixels = Rasterizer.pixels;
			int pixelCount = mmBackgroundPixels.length;

			for (int i = 0; i < pixelCount; i++)
				if (mmBackgroundPixels[i] == 0)
					rasterPixels[i] = 0;

			minimapCompass.shapeImageToPixels(0, 33, 25, 33, anIntArray1286, 0, cameraHorizontal, 256,
					anIntArray1180, 25);
			aClass18_1158.createRasterizer();

			Rasterizer3D.lineOffsets = anIntArray1002;
			return;
		}

		int angle = cameraHorizontal + anInt916 & 0x7ff;
		int centerX = 48 + localPlayer.worldX / 32;
		int centerY = 464 - localPlayer.worldY / 32;

		minimapImage.shapeImageToPixels(5, 151, centerX, 146, anIntArray920,
                25, angle, 256 + anInt1233, anIntArray1019, centerY);
		minimapCompass.shapeImageToPixels(0, 33, 25, 33, anIntArray1286,
                0, cameraHorizontal, 256, anIntArray1180, 25);

		for (int i = 0; i < minimapHintCount; i++) {
			int hintX = (minimapHintX[i] * 4 + 2) - localPlayer.worldX / 32;
			int hintY = (minimapHintY[i] * 4 + 2) - localPlayer.worldY / 32;

			drawOnMinimap(minimapHint[i], hintX, hintY);
		}

		for (int x = 0; x < 104; x++) {
			for (int y = 0; y < 104; y++) {
				LinkedList itemList = groundItems[plane][x][y];

				if (itemList != null) {
					int itemX = (x * 4 + 2) - localPlayer.worldX / 32;
					int itemY = (y * 4 + 2) - localPlayer.worldY / 32;

					drawOnMinimap(mapdotItem, itemX, itemY);
				}
			}

		}

		for (int i = 0; i < anInt1133; i++) {
			Npc npc = npcs[anIntArray1134[i]];

			if (npc != null && npc.isVisible()) {
				ActorDefinition definition = npc.npcDefinition;

				if (definition.childrenIds != null)
					definition = definition.getChildDefinition();

				if (definition != null && definition.minimapVisible && definition.clickable) {
					int npcX = npc.worldX / 32 - localPlayer.worldX / 32;
					int npcY = npc.worldY / 32 - localPlayer.worldY / 32;

					drawOnMinimap(mapdotActor, npcX, npcY);
				}
			}
		}

		for (int i = 0; i < localPlayerCount; i++) {
			Player player = players[playerList[i]];

			if (player != null && player.isVisible()) {
				int playerX = player.worldX / 32 - localPlayer.worldX / 32;
				int playerY = player.worldY / 32 - localPlayer.worldY / 32;
				long name = TextUtils.nameToLong(player.playerName);
                boolean isFriend = false;
                boolean isTeammate = false;

				for (int x = 0; x < friendsCount; x++) {
					if (name != friends[x] || friendWorlds[x] == 0)
						continue;

					isFriend = true;
					break;
				}

				if (localPlayer.teamId != 0 && player.teamId != 0 && localPlayer.teamId == player.teamId)
					isTeammate = true;

				if (isFriend)
					drawOnMinimap(mapdotFriend, playerX, playerY);
				else if (isTeammate)
					drawOnMinimap(mapdotTeammate, playerX, playerY);
				else
					drawOnMinimap(mapdotPlayer, playerX, playerY);
			}
		}

		if (anInt1197 != 0 && pulseCycle % 20 < 10) {
			if (anInt1197 == 1 && anInt1226 >= 0 && anInt1226 < npcs.length) {
				Npc npc = npcs[anInt1226];

				if (npc != null) {
					int npcX = npc.worldX / 32 - localPlayer.worldX / 32;
					int npcY = npc.worldY / 32 - localPlayer.worldY / 32;

					drawMinimap(aClass50_Sub1_Sub1_Sub1_1037, npcX, npcY);
				}
			}

			if (anInt1197 == 2) {
				int hintX = ((anInt844 - nextTopLeftTileX) * 4 + 2) - localPlayer.worldX / 32;
				int hintY = ((anInt845 - nextTopRightTileY) * 4 + 2) - localPlayer.worldY / 32;

				drawMinimap(aClass50_Sub1_Sub1_Sub1_1037, hintX, hintY);
			}

			if (anInt1197 == 10 && anInt1151 >= 0 && anInt1151 < players.length) {
				Player player = players[anInt1151];

				if (player != null) {
					int playerX = player.worldX / 32 - localPlayer.worldX / 32;
					int playerY = player.worldY / 32 - localPlayer.worldY / 32;

					drawMinimap(aClass50_Sub1_Sub1_Sub1_1037, playerX, playerY);
				}
			}
		}

		if (destinationX != 0) {
			int flagX = (destinationX * 4 + 2) - localPlayer.worldX / 32;
			int flagY = (destinationY * 4 + 2) - localPlayer.worldY / 32;

			drawOnMinimap(mapFlagMarker, flagX, flagY);
		}

		Rasterizer.drawFilledRectangle(97, 78, 3, 3, 0xffffff);
		aClass18_1158.createRasterizer();

		Rasterizer3D.lineOffsets = anIntArray1002;
	}

	public URL getCodeBase() {
		if (SignLink.applet != null)
			return SignLink.applet.getCodeBase();

		try {
			if (super.gameFrame != null)
				return new URL("http://" + Configuration.SERVER_ADDRESS + ":" + (Configuration.HTTP_PORT + portOffset));
		} catch (Exception ignored) {}

		return super.getCodeBase();
	}

	public boolean method88(int i, int j, byte byte0) {
		boolean flag = false;
		Widget class13 = Widget.forId(j);
		for (int k = 0; k < class13.children.length; k++) {
			if (class13.children[k] == -1)
				break;
			Widget class13_1 = Widget.forId(class13.children[k]);
			if (class13_1.type == 0)
				flag |= method88(i, class13_1.id, (byte) 5);
			if (class13_1.type == 6 && (class13_1.disabledAnimation != -1 || class13_1.enabledAnimation != -1)) {
				boolean flag1 = method95(class13_1, -693);
				int i1;
				if (flag1)
					i1 = class13_1.enabledAnimation;
				else
					i1 = class13_1.disabledAnimation;
				if (i1 != -1) {
					AnimationSequence class14 = AnimationSequence.animations[i1];
					for (class13_1.anInt227 += i; class13_1.anInt227 > class14.getFrameLength(class13_1.anInt235);) {
						class13_1.anInt227 -= class14.getFrameLength(class13_1.anInt235);
						class13_1.anInt235++;
						if (class13_1.anInt235 >= class14.frameCount) {
							class13_1.anInt235 -= class14.frameStep;
							if (class13_1.anInt235 < 0 || class13_1.anInt235 >= class14.frameCount)
								class13_1.anInt235 = 0;
						}
						flag = true;
					}

				}
			}
			if (class13_1.type == 6 && class13_1.anInt218 != 0) {
				int l = class13_1.anInt218 >> 16;
				int j1 = (class13_1.anInt218 << 16) >> 16;
				l *= i;
				j1 *= i;
				class13_1.rotationX = class13_1.rotationX + l & 0x7ff;
				class13_1.rotationY = class13_1.rotationY + j1 & 0x7ff;
				flag = true;
			}
		}

		if (byte0 == 5)
			byte0 = 0;
		else
			anInt1236 = -424;
		return flag;
	}

	public String method89(int i, int j) {
		if (j < 8 || j > 8)
			throw new NullPointerException();
		if (i < 0x3b9ac9ff)
			return String.valueOf(i);
		else
			return "*";
	}

	public void doLogic() {
		if (aBoolean1016 || aBoolean1283 || aBoolean1097)
			return;
		pulseCycle++;
		if (!loggedIn)
			method149(-724);
		else
			processGame();
		method77(false);
	}

	public void processRightClick(int i) {
		if (activeInterfaceType != 0)
			return;
		menuActionTexts[0] = "Cancel";
		menuActionTypes[0] = 1016;
		menuActionRow = 1;
		if (i >= 0)
			anInt1004 = incomingRandom.nextInt();
		if (anInt1053 != -1) {
			anInt915 = 0;
			anInt1315 = 0;
			method66(0, Widget.forId(anInt1053), 0, 0, 0, super.mouseX, 23658, super.mouseY);
			if (anInt915 != anInt1302)
				anInt1302 = anInt915;
			if (anInt1315 != anInt1129)
				anInt1129 = anInt1315;
			return;
		}
		method111(anInt1178);
		anInt915 = 0;
		anInt1315 = 0;
		if (super.mouseX > 4 && super.mouseY > 4 && super.mouseX < 516 && super.mouseY < 338)
			if (openInterfaceId != -1)
				method66(4, Widget.forId(openInterfaceId), 0, 0, 4, super.mouseX, 23658, super.mouseY);
			else
				method43((byte) 7);
		if (anInt915 != anInt1302)
			anInt1302 = anInt915;
		if (anInt1315 != anInt1129)
			anInt1129 = anInt1315;
		anInt915 = 0;
		anInt1315 = 0;
		if (super.mouseX > 553 && super.mouseY > 205 && super.mouseX < 743 && super.mouseY < 466)
			if (anInt1089 != -1)
				method66(205, Widget.forId(anInt1089), 1, 0, 553, super.mouseX, 23658, super.mouseY);
			else if (anIntArray1081[anInt1285] != -1)
				method66(205, Widget.forId(anIntArray1081[anInt1285]), 1, 0, 553, super.mouseX, 23658,
						super.mouseY);
		if (anInt915 != anInt1280) {
			redrawTabArea = true;
			anInt1280 = anInt915;
		}
		if (anInt1315 != anInt1044) {
			redrawTabArea = true;
			anInt1044 = anInt1315;
		}
		anInt915 = 0;
		anInt1315 = 0;
		if (super.mouseX > 17 && super.mouseY > 357 && super.mouseX < 496 && super.mouseY < 453)
			if (backDialogueId != -1)
				method66(357, Widget.forId(backDialogueId), 2, 0, 17, super.mouseX, 23658, super.mouseY);
			else if (dialogueId != -1)
				method66(357, Widget.forId(dialogueId), 3, 0, 17, super.mouseX, 23658, super.mouseY);
			else if (super.mouseY < 434 && super.mouseX < 426 && inputType == 0)
				method113(466, super.mouseX - 17, super.mouseY - 357);
		if ((backDialogueId != -1 || dialogueId != -1) && anInt915 != anInt1106) {
			redrawChatbox = true;
			anInt1106 = anInt915;
		}
		if ((backDialogueId != -1 || dialogueId != -1) && anInt1315 != anInt1284) {
			redrawChatbox = true;
			anInt1284 = anInt1315;
		}
		for (boolean flag = false; !flag;) {
			flag = true;
			for (int j = 0; j < menuActionRow - 1; j++)
				if (menuActionTypes[j] < 1000 && menuActionTypes[j + 1] > 1000) {
					String s = menuActionTexts[j];
					menuActionTexts[j] = menuActionTexts[j + 1];
					menuActionTexts[j + 1] = s;
					int k = menuActionTypes[j];
					menuActionTypes[j] = menuActionTypes[j + 1];
					menuActionTypes[j + 1] = k;
					k = firstMenuOperand[j];
					firstMenuOperand[j] = firstMenuOperand[j + 1];
					firstMenuOperand[j + 1] = k;
					k = secondMenuOperand[j];
					secondMenuOperand[j] = secondMenuOperand[j + 1];
					secondMenuOperand[j + 1] = k;
					k = selectedMenuActions[j];
					selectedMenuActions[j] = selectedMenuActions[j + 1];
					selectedMenuActions[j + 1] = k;
					flag = false;
				}

		}

	}



	public void method93(int i) {
		try {
			anInt1276 = -1;
			aClass6_1210.getNodeCount();
			aClass6_1282.getNodeCount();
			Rasterizer3D.method495((byte) 71);
			resetModelCaches();
			currentScene.method241();
			System.gc();
			for (int plane = 0; plane < 4; plane++)
				currentCollisionMap[plane].reset();

			for (int i1 = 0; i1 < 4; i1++) {
				for (int l1 = 0; l1 < 104; l1++) {
					for (int k2 = 0; k2 < 104; k2++)
						currentSceneTileFlags[i1][l1][k2] = 0;

				}

			}

			Region class8 = new Region(currentSceneTileFlags, 104, 104, anIntArrayArrayArray891);
			int l2 = aByteArrayArray838.length;
			outBuffer.putOpcode(40);
			if (!aBoolean1163) {
				for (int j3 = 0; j3 < l2; j3++) {
					int j4 = (coordinates[j3] >> 8) * 64 - nextTopLeftTileX;
					int l5 = (coordinates[j3] & 0xff) * 64 - nextTopRightTileY;
					byte abyte0[] = aByteArrayArray838[j3];
					if (abyte0 != null)
						class8.method174(l5, false, (chunkY - 6) * 8, j4, abyte0, (chunkX - 6) * 8,
								currentCollisionMap);
				}

				for (int k4 = 0; k4 < l2; k4++) {
					int i6 = (coordinates[k4] >> 8) * 64 - nextTopLeftTileX;
					int l7 = (coordinates[k4] & 0xff) * 64 - nextTopRightTileY;
					byte abyte2[] = aByteArrayArray838[k4];
					if (abyte2 == null && chunkY < 800)
						class8.initiateVertexHeights(i6, 64, l7, 64);
				}

				outBuffer.putOpcode(40);
				for (int j6 = 0; j6 < l2; j6++) {
					byte abyte1[] = aByteArrayArray1232[j6];
					if (abyte1 != null) {
						int l8 = (coordinates[j6] >> 8) * 64 - nextTopLeftTileX;
						int k9 = (coordinates[j6] & 0xff) * 64 - nextTopRightTileY;
						class8.method179(k9, currentCollisionMap, l8, -571, currentScene, abyte1);
					}
				}

			}
			if (aBoolean1163) {
				for (int k3 = 0; k3 < 4; k3++) {
					for (int l4 = 0; l4 < 13; l4++) {
						for (int k6 = 0; k6 < 13; k6++) {
							boolean flag = false;
							int i9 = constructedMapPalette[k3][l4][k6];
							if (i9 != -1) {
								int l9 = i9 >> 24 & 3;
								int j10 = i9 >> 1 & 3;
								int l10 = i9 >> 14 & 0x3ff;
								int j11 = i9 >> 3 & 0x7ff;
								int l11 = (l10 / 8 << 8) + j11 / 8;
								for (int j12 = 0; j12 < coordinates.length; j12++) {
									if (coordinates[j12] != l11 || aByteArrayArray838[j12] == null)
										continue;
									class8.method168(j10, (j11 & 7) * 8, false, aByteArrayArray838[j12], k3, l9,
											l4 * 8, currentCollisionMap, k6 * 8, (l10 & 7) * 8);
									flag = true;
									break;
								}

							}
							if (!flag)
								class8.method166(anInt1072, k3, k6 * 8, l4 * 8);
						}

					}

				}

				for (int i5 = 0; i5 < 13; i5++) {
					for (int l6 = 0; l6 < 13; l6++) {
						int i8 = constructedMapPalette[0][i5][l6];
						if (i8 == -1)
							class8.initiateVertexHeights(i5 * 8, 8, l6 * 8, 8);
					}

				}

				outBuffer.putOpcode(40);
				for (int i7 = 0; i7 < 4; i7++) {
					for (int j8 = 0; j8 < 13; j8++) {
						for (int j9 = 0; j9 < 13; j9++) {
							int i10 = constructedMapPalette[i7][j8][j9];
							if (i10 != -1) {
								int k10 = i10 >> 24 & 3;
								int i11 = i10 >> 1 & 3;
								int k11 = i10 >> 14 & 0x3ff;
								int i12 = i10 >> 3 & 0x7ff;
								int k12 = (k11 / 8 << 8) + i12 / 8;
								for (int l12 = 0; l12 < coordinates.length; l12++) {
									if (coordinates[l12] != k12 || aByteArrayArray1232[l12] == null)
										continue;
									class8.method172(i7, currentCollisionMap, currentScene, false,
											aByteArrayArray1232[l12], j9 * 8, i11, (k11 & 7) * 8, j8 * 8,
											(i12 & 7) * 8, k10);
									break;
								}

							}
						}

					}

				}

			}
			outBuffer.putOpcode(40);
			class8.createRegionScene(currentCollisionMap, currentScene);
			if (aClass18_1158 != null) {
				aClass18_1158.createRasterizer();
				Rasterizer3D.lineOffsets = anIntArray1002;
			}
			outBuffer.putOpcode(40);
			int l3 = Region.lowestPlane;
			if (l3 > plane)
				l3 = plane;
			if (l3 < plane - 1)
				l3 = plane - 1;
			if (lowMemory)
				currentScene.method242(Region.lowestPlane);
			else
				currentScene.method242(0);
			for (int j5 = 0; j5 < 104; j5++) {
				for (int j7 = 0; j7 < 104; j7++)
					processGroundItems(j5, j7);

			}

			method18((byte) 3);
		} catch (Exception exception) {
		}
		GameObjectDefinition.modelCache.removeAll();
		if (super.gameFrame != null) {
			outBuffer.putOpcode(78);
			outBuffer.putInt(0x3f008edd);
		}
		if (lowMemory && SignLink.cacheData != null) {
			int k = onDemandRequester.fileCount(0);
			for (int j1 = 0; j1 < k; j1++) {
				int i2 = onDemandRequester.modelId(j1);
				if ((i2 & 0x79) == 0)
					Model.resetModel(j1);
			}

		}
		System.gc();
		Rasterizer3D.method496(20);
		onDemandRequester.immediateRequestCount();
		int l = (chunkX - 6) / 8 - 1;
		int k1 = (chunkX + 6) / 8 + 1;
		int j2 = (chunkY - 6) / 8 - 1;
		int i3 = (chunkY + 6) / 8 + 1;
		i = 94 / i;
		if (aBoolean1067) {
			l = 49;
			k1 = 50;
			j2 = 49;
			i3 = 50;
		}
		for (int i4 = l; i4 <= k1; i4++) {
			for (int k5 = j2; k5 <= i3; k5++)
				if (i4 == l || i4 == k1 || k5 == j2 || k5 == i3) {
					int k7 = onDemandRequester.regId(0, i4, k5, 0);
					if (k7 != -1)
						onDemandRequester.passiveRequest(k7, 3);
					int k8 = onDemandRequester.regId(0, i4, k5, 1);
					if (k8 != -1)
						onDemandRequester.passiveRequest(k8, 3);
				}

		}

	}

	private void setCameraPosition(int x, int y, int z, int pitch, int yaw) {
		int pitchDifference = 2048 - pitch & 0x7ff;
		int yawDifference = 2048 - yaw & 0x7ff;
		int xOffset = 0;
		int zOffset = 0;
		int yOffset = 600 + pitch * 3;

		if (pitchDifference != 0) {
			int sine = Model.SINE[pitchDifference];
			int cosine = Model.COSINE[pitchDifference];
			int temp = zOffset * cosine - yOffset * sine >> 16;
			yOffset = zOffset * sine + yOffset * cosine >> 16;
			zOffset = temp;
		}

		if (yawDifference != 0) {
			int sine = Model.SINE[yawDifference];
			int cosine = Model.COSINE[yawDifference];
			int temp = yOffset * sine + xOffset * cosine >> 16;
			yOffset = yOffset * cosine - xOffset * sine >> 16;
			xOffset = temp;
		}

		cameraX = x - xOffset;
		cameraZ = z - zOffset;
		cameraY = y - yOffset;
		anInt1219 = pitch;
		anInt1220 = yaw;
	}

	public boolean method95(Widget class13, int i) {
		if (i >= 0)
			anInt1175 = 276;
		if (class13.conditionTypes == null)
			return false;
		for (int j = 0; j < class13.conditionTypes.length; j++) {
			int k = method129(3, j, class13);
			int l = class13.conditionValues[j];
			if (class13.conditionTypes[j] == 2) {
				if (k >= l)
					return false;
			} else if (class13.conditionTypes[j] == 3) {
				if (k <= l)
					return false;
			} else if (class13.conditionTypes[j] == 4) {
				if (k == l)
					return false;
			} else if (k != l)
				return false;
		}

		return true;
	}

	private void updatePlayers(int size, Buffer buffer) {
		removePlayerCount = 0;
		updatedPlayerCount = 0;

		updateLocalPlayerMovement(buffer);
		updateOtherPlayerMovement(buffer);
		addNewPlayers(size, buffer);
		parsePlayerBlocks(buffer);

		for (int i = 0; i < removePlayerCount; i++) {
			int index = removePlayers[i];

			if (players[index].pulseCycle != pulseCycle)
				players[index] = null;
		}

		if (buffer.currentPosition != size) {
			SignLink.reportError("Error packet size mismatch in getplayer coord:" + buffer.currentPosition + " psize:" + size);
			throw new RuntimeException("eek");
		}

		for (int i = 0; i < localPlayerCount; i++) {
			if (players[playerList[i]] == null) {
				SignLink.reportError(username + " null entry in pl list - coord:" + i + " size:" + localPlayerCount);
				throw new RuntimeException("eek");
			}
		}
	}

	public void removeIgnore(int i, long l) {
		try {
			if (l == 0L)
				return;
			for (int j = 0; j < ignoresCount; j++) {
				if (ignores[j] != l)
					continue;
				ignoresCount--;
				redrawTabArea = true;
				for (int k = j; k < ignoresCount; k++)
					ignores[k] = ignores[k + 1];

				outBuffer.putOpcode(160);
				outBuffer.putLong(l);
				break;
			}

			i = 42 / i;
			return;
		} catch (RuntimeException runtimeexception) {
			SignLink.reportError("45745, " + i + ", " + l + ", " + runtimeexception.toString());
		}
		throw new RuntimeException();
	}

	public String getParameter(String s) {
		if (SignLink.applet != null)
			return SignLink.applet.getParameter(s);
		else
			return super.getParameter(s);
	}

	private void renderFlames() { //TODO: Needs more refactoring
		int c = 256;

		if (anInt1047 > 0) {
			for (int j = 0; j < 256; j++) {
				if (anInt1047 > 768)
					anIntArray1310[j] = method106(anIntArray1311[j], anIntArray1312[j], 1024 - anInt1047, 8);
				else if (anInt1047 > 256)
					anIntArray1310[j] = anIntArray1312[j];
				else
					anIntArray1310[j] = method106(anIntArray1312[j], anIntArray1311[j], 256 - anInt1047, 8);
			}
		} else if (anInt1048 > 0) {
			for (int k = 0; k < 256; k++) {
				if (anInt1048 > 768)
					anIntArray1310[k] = method106(anIntArray1311[k], anIntArray1313[k], 1024 - anInt1048, 8);
				else if (anInt1048 > 256)
					anIntArray1310[k] = anIntArray1313[k];
				else
					anIntArray1310[k] = method106(anIntArray1313[k], anIntArray1311[k], 256 - anInt1048, 8);
			}
		} else {
			System.arraycopy(anIntArray1311, 0, anIntArray1310, 0, 256);
		}

		System.arraycopy(anImageRGB1226.pixels, 0, flameLeftBackground.pixels, 0, 33920);

		int j1 = 0;
		int k1 = 1152;

		for (int l1 = 1; l1 < c - 1; l1++) {
			int i2 = (anIntArray1166[l1] * (c - l1)) / c;
			int k2 = 22 + i2;

			if (k2 < 0)
				k2 = 0;

			j1 += k2;

			for (int i3 = k2; i3 < 128; i3++) {
				int k3 = anIntArray1084[j1++];

				if (k3 != 0) {
					int i4 = k3;
					int k4 = 256 - k3;
					k3 = anIntArray1310[k3];
					int i5 = flameLeftBackground.pixels[k1];
					flameLeftBackground.pixels[k1++] = ((k3 & 0xff00ff) * i4 + (i5 & 0xff00ff) * k4 & 0xff00ff00)
							+ ((k3 & 0xff00) * i4 + (i5 & 0xff00) * k4 & 0xff0000) >> 8;
				} else {
					k1++;
				}
			}

			k1 += k2;
		}

		flameLeftBackground.drawGraphics(0, 0, super.gameGraphics);

		System.arraycopy(anImageRGB1227.pixels, 0, flameRightBackground.pixels, 0, 33920);

		j1 = 0;
		k1 = 1176;

		for (int l2 = 1; l2 < c - 1; l2++) {
			int j3 = (anIntArray1166[l2] * (c - l2)) / c;
			int l3 = 103 - j3;
			k1 += j3;

			for (int j4 = 0; j4 < l3; j4++) {
				int l4 = anIntArray1084[j1++];

				if (l4 != 0) {
					int j5 = l4;
					int k5 = 256 - l4;
					l4 = anIntArray1310[l4];
					int l5 = flameRightBackground.pixels[k1];
					flameRightBackground.pixels[k1++] = ((l4 & 0xff00ff) * j5 + (l5 & 0xff00ff) * k5 & 0xff00ff00)
							+ ((l4 & 0xff00) * j5 + (l5 & 0xff00) * k5 & 0xff0000) >> 8;
				} else {
					k1++;
				}
			}

			j1 += 128 - l3;
			k1 += 128 - l3 - j3;
		}

		flameRightBackground.drawGraphics(637, 0, super.gameGraphics);
	}

	public void method100(int i) {
		for (int j = -1; j < localPlayerCount; j++) {
			int k;
			if (j == -1)
				k = thisPlayerId;
			else
				k = playerList[j];
			Player class50_sub1_sub4_sub3_sub2 = players[k];
			if (class50_sub1_sub4_sub3_sub2 != null)
				method68(1, (byte) -97, class50_sub1_sub4_sub3_sub2);
		}

		if (i < anInt1222 || i > anInt1222) {
			for (int l = 1; l > 0; l++);
		}
	}

	public void method103(byte byte0, Widget class13) {
		if (byte0 == 2)
			byte0 = 0;
		else
			anInt1004 = -82;
		int i = class13.contentType;
		if (i >= 1 && i <= 100 || i >= 701 && i <= 800) {
			if (i == 1 && friendListStatus == 0) {
				class13.disabledText = "Loading friend list";
				class13.actionType = 0;
				return;
			}
			if (i == 1 && friendListStatus == 1) {
				class13.disabledText = "Connecting to friendserver";
				class13.actionType = 0;
				return;
			}
			if (i == 2 && friendListStatus != 2) {
				class13.disabledText = "Please wait...";
				class13.actionType = 0;
				return;
			}
			int j = friendsCount;
			if (friendListStatus != 2)
				j = 0;
			if (i > 700)
				i -= 601;
			else
				i--;
			if (i >= j) {
				class13.disabledText = "";
				class13.actionType = 0;
				return;
			} else {
				class13.disabledText = friendUsernames[i];
				class13.actionType = 1;
				return;
			}
		}
		if (i >= 101 && i <= 200 || i >= 801 && i <= 900) {
			int k = friendsCount;
			if (friendListStatus != 2)
				k = 0;
			if (i > 800)
				i -= 701;
			else
				i -= 101;
			if (i >= k) {
				class13.disabledText = "";
				class13.actionType = 0;
				return;
			}
			if (friendWorlds[i] == 0)
				class13.disabledText = "@red@Offline";
			else if (friendWorlds[i] < 200) {
				if (friendWorlds[i] == world)
					class13.disabledText = "@gre@World" + (friendWorlds[i] - 9);
				else
					class13.disabledText = "@yel@World" + (friendWorlds[i] - 9);
			} else if (friendWorlds[i] == world)
				class13.disabledText = "@gre@Classic" + (friendWorlds[i] - 219);
			else
				class13.disabledText = "@yel@Classic" + (friendWorlds[i] - 219);
			class13.actionType = 1;
			return;
		}
		if (i == 203) {
			int l = friendsCount;
			if (friendListStatus != 2)
				l = 0;
			class13.scrollLimit = l * 15 + 20;
			if (class13.scrollLimit <= class13.height)
				class13.scrollLimit = class13.height + 1;
			return;
		}
		if (i >= 401 && i <= 500) {
			if ((i -= 401) == 0 && friendListStatus == 0) {
				class13.disabledText = "Loading ignore list";
				class13.actionType = 0;
				return;
			}
			if (i == 1 && friendListStatus == 0) {
				class13.disabledText = "Please wait...";
				class13.actionType = 0;
				return;
			}
			int i1 = ignoresCount;
			if (friendListStatus == 0)
				i1 = 0;
			if (i >= i1) {
				class13.disabledText = "";
				class13.actionType = 0;
				return;
			} else {
				class13.disabledText = TextUtils.formatName(TextUtils.longToName(ignores[i]));
				class13.actionType = 1;
				return;
			}
		}
		if (i == 503) {
			class13.scrollLimit = ignoresCount * 15 + 20;
			if (class13.scrollLimit <= class13.height)
				class13.scrollLimit = class13.height + 1;
			return;
		}
		if (i == 327) {
			class13.rotationX = 150;
			class13.rotationY = (int) (Math.sin((double) pulseCycle / 40D) * 256D) & 0x7ff;
			if (aBoolean1277) {
				for (int j1 = 0; j1 < 7; j1++) {
					int i2 = characterEditIdentityKits[j1];
					if (i2 >= 0 && !IdentityKit.cache[i2].isBodyModelCached())
						return;
				}

				aBoolean1277 = false;
				Model aclass50_sub1_sub4_sub4[] = new Model[7];
				int j2 = 0;
				for (int k2 = 0; k2 < 7; k2++) {
					int l2 = characterEditIdentityKits[k2];
					if (l2 >= 0)
						aclass50_sub1_sub4_sub4[j2++] = IdentityKit.cache[l2].getBodyModel();
				}

				Model class50_sub1_sub4_sub4 = new Model(j2, aclass50_sub1_sub4_sub4);
				for (int i3 = 0; i3 < 5; i3++)
					if (characterEditColors[i3] != 0) {
						class50_sub1_sub4_sub4.replaceColor(playerColours[i3][0],
								playerColours[i3][characterEditColors[i3]]);
						if (i3 == 1)
							class50_sub1_sub4_sub4.replaceColor(SKIN_COLOURS[0], SKIN_COLOURS[characterEditColors[i3]]);
					}

				class50_sub1_sub4_sub4.createBones();
				class50_sub1_sub4_sub4.applyTransform(
						AnimationSequence.animations[localPlayer.idleAnimation].getPrimaryFrame[0]);
				class50_sub1_sub4_sub4.applyLighting(64, 850, -30, -50, -30, true);
				class13.modelType = 5;
				class13.modelId = 0;
				Widget.setModel(5, class50_sub1_sub4_sub4, 0);
			}
			return;
		}
		if (i == 324) {
			if (aClass50_Sub1_Sub1_Sub1_1102 == null) {
				aClass50_Sub1_Sub1_Sub1_1102 = class13.disabledImage;
				aClass50_Sub1_Sub1_Sub1_1103 = class13.enabledImage;
			}
			if (characterEditChangeGenger) {
				class13.disabledImage = aClass50_Sub1_Sub1_Sub1_1103;
				return;
			} else {
				class13.disabledImage = aClass50_Sub1_Sub1_Sub1_1102;
				return;
			}
		}
		if (i == 325) {
			if (aClass50_Sub1_Sub1_Sub1_1102 == null) {
				aClass50_Sub1_Sub1_Sub1_1102 = class13.disabledImage;
				aClass50_Sub1_Sub1_Sub1_1103 = class13.enabledImage;
			}
			if (characterEditChangeGenger) {
				class13.disabledImage = aClass50_Sub1_Sub1_Sub1_1102;
				return;
			} else {
				class13.disabledImage = aClass50_Sub1_Sub1_Sub1_1103;
				return;
			}
		}
		if (i == 600) {
			class13.disabledText = reportedName;
			if (pulseCycle % 20 < 10) {
				class13.disabledText += "|";
				return;
			} else {
				class13.disabledText += " ";
				return;
			}
		}
		if (i == 620)
			if (playerRights >= 1) {
				if (reportMutePlayer) {
					class13.disabledColor = 0xff0000;
					class13.disabledText = "Moderator option: Mute player for 48 hours: <ON>";
				} else {
					class13.disabledColor = 0xffffff;
					class13.disabledText = "Moderator option: Mute player for 48 hours: <OFF>";
				}
			} else {
				class13.disabledText = "";
			}
		if (i == 660) {
			int k1 = anInt1170 - anInt1215;
			String s1;
			if (k1 <= 0)
				s1 = "earlier today";
			else if (k1 == 1)
				s1 = "yesterday";
			else
				s1 = k1 + " days ago";
			class13.disabledText = "You last logged in @red@" + s1 + "@bla@ from: @red@" + SignLink.dns;
		}
		if (i == 661)
			if (anInt1034 == 0)
				class13.disabledText = "\\nYou have not yet set any recovery questions.\\nIt is @lre@strongly@yel@ recommended that you do so.\\n\\nIf you don't you will be @lre@unable to recover your\\n@lre@password@yel@ if you forget it, or it is stolen.";
			else if (anInt1034 <= anInt1170) {
				class13.disabledText = "\\n\\nRecovery Questions Last Set:\\n@gre@" + getDate(anInt1034);
			} else {
				int l1 = (anInt1170 + 14) - anInt1034;
				String s2;
				if (l1 <= 0)
					s2 = "Earlier today";
				else if (l1 == 1)
					s2 = "Yesterday";
				else
					s2 = l1 + " days ago";
				class13.disabledText = s2
						+ " you requested@lre@ new recovery\\n@lre@questions.@yel@ The requested change will occur\\non: @lre@"
						+ getDate(anInt1034)
						+ "\\n\\nIf you do not remember making this request\\ncancel it immediately, and change your password.";
			}
		if (i == 662) {
			String s;
			if (anInt1273 == 0)
				s = "@yel@0 unread messages";
			else if (anInt1273 == 1)
				s = "@gre@1 unread message";
			else
				s = "@gre@" + anInt1273 + " unread messages";
			class13.disabledText = "You have " + s + "\\nin your message centre.";
		}
		if (i == 663)
			if (anInt1083 <= 0 || anInt1083 > anInt1170 + 10)
				class13.disabledText = "Last password change:\\n@gre@Never changed";
			else
				class13.disabledText = "Last password change:\\n@gre@" + getDate(anInt1083);
		if (i == 665)
			if (anInt992 > 2 && !memberServer)
				class13.disabledText = "This is a non-members\\nworld. To enjoy your\\nmembers benefits we\\nrecommend you play on a\\nmembers world instead.";
			else if (anInt992 > 2)
				class13.disabledText = "\\n\\nYou have @gre@" + anInt992 + "@yel@ days of\\nmember credit remaining.";
			else if (anInt992 > 0)
				class13.disabledText = "You have @gre@"
						+ anInt992
						+ "@yel@ days of\\nmember credit remaining.\\n\\n@lre@Credit low! Renew now\\n@lre@to avoid losing members.";
			else
				class13.disabledText = "You are not a member.\\n\\nChoose to subscribe and\\nyou'll get loads of extra\\nbenefits and features.";
		if (i == 667)
			if (anInt992 > 2 && !memberServer)
				class13.disabledText = "To switch to a members-only world:\\n1) Logout and return to the world selection page.\\n2) Choose one of the members world with a gold star next to it's name.\\n\\nIf you prefer you can continue to use this world,\\nbut members only features will be unavailable here.";
			else if (anInt992 > 0)
				class13.disabledText = "To extend or cancel a subscription:\\n1) Logout and return to the frontpage of this website.\\n2)Choose the relevant option from the 'membership' section.\\n\\nNote: If you are a credit card subscriber a top-up payment will\\nautomatically be taken when 3 days credit remain.\\n(unless you cancel your subscription, which can be done at any time.)";
			else
				class13.disabledText = "To initializeApplication a subscripton:\\n1) Logout and return to the frontpage of this website.\\n2) Choose 'Start a new subscription'";
		if (i == 668) {
			if (anInt1034 > anInt1170) {
				class13.disabledText = "To cancel this request:\\n1) Logout and return to the frontpage of this website.\\n2) Choose 'Cancel recovery questions'.";
				return;
			}
			class13.disabledText = "To change your recovery questions:\\n1) Logout and return to the frontpage of this website.\\n2) Choose 'Set new recovery questions'.";
		}
	}

	private String getDate(int time) {
		if (time > anInt1170 + 10) {
			return "Unknown";
		} else {
			long date = ((long) time + 11745L) * 0x5265c00L;
			Calendar calendar = Calendar.getInstance();
			
			calendar.setTime(new Date(date));
			
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			int month = calendar.get(Calendar.MONTH);
			int year = calendar.get(Calendar.YEAR);
			String[] monthNames = { "Jan", "Feb", "Mar", "Apr", "May",
                    "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
			
			return day + "-" + monthNames[month] + "-" + year;
		}
	}

	public void updateVarp(int i, int j) {
		packetSize += i;
		int action = Varp.cache[j].anInt712;
		if (action == 0)
			return;
		int config = widgetSettings[j];
		if (action == 1) {
			if (config == 1)
				Rasterizer3D.method501(0.90000000000000002D);
			if (config == 2)
				Rasterizer3D.method501(0.80000000000000004D);
			if (config == 3)
				Rasterizer3D.method501(0.69999999999999996D);
			if (config == 4)
				Rasterizer3D.method501(0.59999999999999998D);
			ItemDefinition.rgbImageCache.removeAll();
			aBoolean1046 = true;
		}
		if (action == 3) {
			boolean flag = musicEnabled;
			if (config == 0) {
				adjustMidiVolume(musicEnabled, (byte) 8, 0);
				musicEnabled = true;
			}
			if (config == 1) {
				adjustMidiVolume(musicEnabled, (byte) 8, -400);
				musicEnabled = true;
			}
			if (config == 2) {
				adjustMidiVolume(musicEnabled, (byte) 8, -800);
				musicEnabled = true;
			}
			if (config == 3) {
				adjustMidiVolume(musicEnabled, (byte) 8, -1200);
				musicEnabled = true;
			}
			if (config == 4)
				musicEnabled = false;
			if (musicEnabled != flag && !lowMemory) {
				if (musicEnabled) {
					nextSong = currentSong;
					songChanging = true;
					onDemandRequester.request(2, nextSong);
				} else {
					stopMidi();
				}
				previousSong = 0;
			}
		}
		if (action == 4) {
			SoundPlayer.setVolume(config);
			if (config == 0) {
				aBoolean1301 = true;
				setWaveVolume(0);
			}
			if (config == 1) {
				aBoolean1301 = true;
				setWaveVolume(-400);
			}
			if (config == 2) {
				aBoolean1301 = true;
				setWaveVolume(-800);
			}
			if (config == 3) {
				aBoolean1301 = true;
				setWaveVolume(-1200);
			}
			if (config == 4)
				aBoolean1301 = false;
		}
		if (action == 5)
			anInt1300 = config;
		if (action == 6)
			anInt998 = config;
		if (action == 8) {
			anInt1223 = config;
			redrawChatbox = true;
		}
		if (action == 9)
			anInt955 = config;
	}

	public int method106(int i, int j, int k, int l) {
		if (l < 8 || l > 8)
			outBuffer.putByte(235);
		int i1 = 256 - k;
		return ((i & 0xff00ff) * i1 + (j & 0xff00ff) * k & 0xff00ff00)
				+ ((i & 0xff00) * i1 + (j & 0xff00) * k & 0xff0000) >> 8;
	}

	private void setTutorialIslandFlag() {
		int x = (localPlayer.worldX >> 7) + nextTopLeftTileX;
		int y = (localPlayer.worldY >> 7) + nextTopRightTileY;
        inTutorialIsland = false;

		if (x >= 3053 && x <= 3156 && y >= 3056 && y <= 3136)
			inTutorialIsland = true;
		if (x >= 3072 && x <= 3118 && y >= 9492 && y <= 9535)
			inTutorialIsland = true;
		if (inTutorialIsland && x >= 3139 && x <= 3199 && y >= 3008 && y <= 3062)
			inTutorialIsland = false;
	}

	private void determineMenuSize() {
		int width = fontBold.getStringEffectWidth("Choose Option");

		for (int i = 0; i < menuActionRow; i++) {
			int rowWidth = fontBold.getStringEffectWidth(menuActionTexts[i]);

			if (rowWidth > width)
				width = rowWidth;
		}

		width += 8;
		int height = 15 * menuActionRow + 21;

		if (super.clickX > 4 && super.clickY > 4 && super.clickX < 516 && super.clickY < 338) {
			int x = super.clickX - 4 - width / 2;

			if (x + width > 512)
				x = 512 - width;
			if (x < 0)
				x = 0;

			int y = super.clickY - 4;

			if (y + height > 334)
				y = 334 - height;
			if (y < 0)
				y = 0;

			menuOpen = true;
			anInt1304 = 0;
			menuClickX = x;
			menuClickY = y;
			anInt1307 = width;
			anInt1308 = height + 1;
		}

		if (super.clickX > 553 && super.clickY > 205 && super.clickX < 743 && super.clickY < 466) {
			int x = super.clickX - 553 - width / 2;

			if (x < 0)
				x = 0;
			else if (x + width > 190)
				x = 190 - width;

			int y = super.clickY - 205;

			if (y < 0)
				y = 0;
			else if (y + height > 261)
				y = 261 - height;

			menuOpen = true;
			anInt1304 = 1;
			menuClickX = x;
			menuClickY = y;
			anInt1307 = width;
			anInt1308 = height + 1;
		}

		if (super.clickX > 17 && super.clickY > 357 && super.clickX < 496 && super.clickY < 453) {
			int x = super.clickX - 17 - width / 2;

			if (x < 0)
				x = 0;
			else if (x + width > 479)
				x = 479 - width;

			int y = super.clickY - 357;

			if (y < 0)
				y = 0;
			else if (y + height > 96)
				y = 96 - height;

			menuOpen = true;
			anInt1304 = 2;
			menuClickX = x;
			menuClickY = y;
			anInt1307 = width;
			anInt1308 = height + 1;
		}
	}

	private void renderGameView() {
		renderSplitPrivateMessages();

		if (crossType == 1)
			cursorCross[crossIndex / 100].drawImage(anInt1021 - 8 - 4, anInt1020 - 8 - 4);
		if (crossType == 2)
			cursorCross[4 + crossIndex / 100].drawImage(anInt1021 - 8 - 4, anInt1020 - 8 - 4);

		if (anInt1279 != -1) {
			method88(tickDelta, anInt1279, (byte) 5);
			method142(0, 0, Widget.forId(anInt1279), 0, 8);
		}

		if (openInterfaceId != -1) {
			method88(tickDelta, openInterfaceId, (byte) 5);
			method142(0, 0, Widget.forId(openInterfaceId), 0, 8);
		}

		setTutorialIslandFlag();

		if (!menuOpen) {
			processRightClick(-521);
			drawMenuTooltip();
		} else if (anInt1304 == 0) {
            method128(false);
        }

		if (anInt1319 == 1)
			aClass50_Sub1_Sub1_Sub1_1086.drawImage(296, 472);

		if (fps) {
            int y = 20;
			int colour = 0xffff00;

			if (super.fps < 30 && lowMemory)
				colour = 0xff0000;
			if (super.fps < 20 && !lowMemory)
				colour = 0xff0000;

			fontNormal.drawStringRight("Fps:" + super.fps, 507, y, colour);

			y += 15;
			Runtime runtime = Runtime.getRuntime();
			int memoryUsed = (int) ((runtime.totalMemory() - runtime.freeMemory()) / 1024L);
			colour = 0xffff00;

			if (memoryUsed > 0x2000000 && lowMemory)
				colour = 0xff0000;
			if (memoryUsed > 0x4000000 && !lowMemory)
				colour = 0xff0000;

			fontNormal.drawStringRight("Mem:" + memoryUsed + "k", 507, y, colour);
		}

		if (systemUpdateTime != 0) {
			int seconds = systemUpdateTime / 50;
			int minutes = seconds / 60;
			seconds %= 60;

			if (seconds < 10)
				fontNormal.drawString("System update in: " + minutes + ":0" + seconds, 4, 329, 0xffff00);
			else
				fontNormal.drawString("System update in: " + minutes + ":" + seconds, 4, 329, 0xffff00);

			anInt895++;

			if (anInt895 > 112) {
				anInt895 = 0;
				outBuffer.putOpcode(197);
				outBuffer.putInt(0);
			}
		}
	}

	public void run() {
		if (aBoolean1314) {
			processFlamesCycle();
		} else {
			super.run();
		}
	}

	public int method110(int i, int j, byte byte0, int k) {
		int l = j >> 7;
		int i1 = i >> 7;
		if (l < 0 || i1 < 0 || l > 103 || i1 > 103)
			return 0;
		int j1 = k;
		if (j1 < 3 && (currentSceneTileFlags[1][l][i1] & 2) == 2)
			j1++;
		int k1 = j & 0x7f;
		int l1 = i & 0x7f;
		if (byte0 != 9)
			aBoolean953 = !aBoolean953;
		int i2 = anIntArrayArrayArray891[j1][l][i1] * (128 - k1) + anIntArrayArrayArray891[j1][l + 1][i1] * k1 >> 7;
		int j2 = anIntArrayArrayArray891[j1][l][i1 + 1] * (128 - k1) + anIntArrayArrayArray891[j1][l + 1][i1 + 1] * k1 >> 7;
		return i2 * (128 - l1) + j2 * l1 >> 7;
	}

	public AppletContext getAppletContext() {
		if (SignLink.applet != null)
			return SignLink.applet.getAppletContext();
		else
			return super.getAppletContext();
	}

	public void method111(int i) {
		i = 21 / i;
		if (anInt1223 == 0)
			return;
		int j = 0;
		if (systemUpdateTime != 0)
			j = 1;
		for (int k = 0; k < 100; k++)
			if (chatMessages[k] != null) {
				int l = chatTypes[k];
				String s = chatPlayerNames[k];
				if (s != null && s.startsWith("@cr1@")) {
					s = s.substring(5);
				}
				if (s != null && s.startsWith("@cr2@")) {
					s = s.substring(5);
				}
				if ((l == 3 || l == 7) && (l == 7 || privateChatMode == 0 || privateChatMode == 1 && method148(13292, s))) {
					int i1 = 329 - j * 13;
					if (super.mouseX > 4 && super.mouseY - 4 > i1 - 10 && super.mouseY - 4 <= i1 + 3) {
						int j1 = fontNormal.getStringEffectWidth("From:  " + s + chatMessages[k]) + 25;
						if (j1 > 450)
							j1 = 450;
						if (super.mouseX < 4 + j1) {
							if (playerRights >= 1) {
								menuActionTexts[menuActionRow] = "Report abuse @whi@" + s;
								menuActionTypes[menuActionRow] = 2507;
								menuActionRow++;
							}
							menuActionTexts[menuActionRow] = "Add ignore @whi@" + s;
							menuActionTypes[menuActionRow] = 2574;
							menuActionRow++;
							menuActionTexts[menuActionRow] = "Add friend @whi@" + s;
							menuActionTypes[menuActionRow] = 2762;
							menuActionRow++;
						}
					}
					if (++j >= 5)
						return;
				}
				if ((l == 5 || l == 6) && privateChatMode < 2 && ++j >= 5)
					return;
			}

	}

	public void method112(byte byte0, int i) {
		if (byte0 != 36)
			outBuffer.putByte(6);
		Widget class13 = Widget.forId(i);
		for (int j = 0; j < class13.children.length; j++) {
			if (class13.children[j] == -1)
				break;
			Widget class13_1 = Widget.forId(class13.children[j]);
			if (class13_1.type == 1)
				method112((byte) 36, class13_1.id);
			class13_1.anInt235 = 0;
			class13_1.anInt227 = 0;
		}

	}

	public void method113(int i, int j, int k) {
		int l = 0;
		i = 44 / i;
		for (int i1 = 0; i1 < 100; i1++) {
			if (chatMessages[i1] == null)
				continue;
			int j1 = chatTypes[i1];
			int k1 = (70 - l * 14) + anInt851 + 4;
			if (k1 < -20)
				break;
			String s = chatPlayerNames[i1];
			if (s != null && s.startsWith("@cr1@")) {
				s = s.substring(5);
			}
			if (s != null && s.startsWith("@cr2@")) {
				s = s.substring(5);
			}
			if (j1 == 0)
				l++;
			if ((j1 == 1 || j1 == 2) && (j1 == 1 || publicChatMode == 0 || publicChatMode == 1 && method148(13292, s))) {
				if (k > k1 - 14 && k <= k1 && !s.equals(localPlayer.playerName)) {
					if (playerRights >= 1) {
						menuActionTexts[menuActionRow] = "Report abuse @whi@" + s;
						menuActionTypes[menuActionRow] = 507;
						menuActionRow++;
					}
					menuActionTexts[menuActionRow] = "Add ignore @whi@" + s;
					menuActionTypes[menuActionRow] = 574;
					menuActionRow++;
					menuActionTexts[menuActionRow] = "Add friend @whi@" + s;
					menuActionTypes[menuActionRow] = 762;
					menuActionRow++;
				}
				l++;
			}
			if ((j1 == 3 || j1 == 7) && anInt1223 == 0
					&& (j1 == 7 || privateChatMode == 0 || privateChatMode == 1 && method148(13292, s))) {
				if (k > k1 - 14 && k <= k1) {
					if (playerRights >= 1) {
						menuActionTexts[menuActionRow] = "Report abuse @whi@" + s;
						menuActionTypes[menuActionRow] = 507;
						menuActionRow++;
					}
					menuActionTexts[menuActionRow] = "Add ignore @whi@" + s;
					menuActionTypes[menuActionRow] = 574;
					menuActionRow++;
					menuActionTexts[menuActionRow] = "Add friend @whi@" + s;
					menuActionTypes[menuActionRow] = 762;
					menuActionRow++;
				}
				l++;
			}
			if (j1 == 4 && (tradeMode == 0 || tradeMode == 1 && method148(13292, s))) {
				if (k > k1 - 14 && k <= k1) {
					menuActionTexts[menuActionRow] = "Accept trade @whi@" + s;
					menuActionTypes[menuActionRow] = 544;
					menuActionRow++;
				}
				l++;
			}
			if ((j1 == 5 || j1 == 6) && anInt1223 == 0 && privateChatMode < 2)
				l++;
			if (j1 == 8 && (tradeMode == 0 || tradeMode == 1 && method148(13292, s))) {
				if (k > k1 - 14 && k <= k1) {
					menuActionTexts[menuActionRow] = "Accept challenge @whi@" + s;
					menuActionTypes[menuActionRow] = 695;
					menuActionRow++;
				}
				l++;
			}
		}

	}

	private void updateOtherPlayerMovement(Buffer buffer) {
		int playerCount = buffer.getBits(8);

		if (playerCount < localPlayerCount) {
			for (int i = playerCount; i < localPlayerCount; i++)
				removePlayers[removePlayerCount++] = playerList[i];
		}

		if (playerCount > localPlayerCount) {
			SignLink.reportError(username + " Too many players");
			throw new RuntimeException("eek");
		}

		localPlayerCount = 0;

		for (int i = 0; i < playerCount; i++) {
			int id = playerList[i];
			Player player = players[id];
			int updated = buffer.getBits(1);

			if (updated == 0) {
				playerList[localPlayerCount++] = id;
				player.pulseCycle = pulseCycle;
			} else {
				int moveType = buffer.getBits(2);

				if (moveType == 0) {
					playerList[localPlayerCount++] = id;
					player.pulseCycle = pulseCycle;
					updatedPlayers[updatedPlayerCount++] = id;
				} else if (moveType == 1) {
					playerList[localPlayerCount++] = id;
					player.pulseCycle = pulseCycle;
					int direction = buffer.getBits(3);

					player.move(direction, false);

					int blockUpdateRequired = buffer.getBits(1);

					if (blockUpdateRequired == 1)
						updatedPlayers[updatedPlayerCount++] = id;
				} else if (moveType == 2) {
					playerList[localPlayerCount++] = id;
					player.pulseCycle = pulseCycle;
					int direction1 = buffer.getBits(3);

					player.move(direction1, true);

					int direction2 = buffer.getBits(3);

					player.move(direction2, true);

					int updateRequired = buffer.getBits(1);

					if (updateRequired == 1)
						updatedPlayers[updatedPlayerCount++] = id;
				} else if (moveType == 3) {
                    removePlayers[removePlayerCount++] = id;
                }
			}
		}
	}

	public void renderViewport(int plane) {
		int pixels[] = minimapImage.pixels;
		int pixelAmount = pixels.length;
		for (int pixel = 0; pixel < pixelAmount; pixel++)
			pixels[pixel] = 0;

		for (int viewportY = 1; viewportY < 103; viewportY++) {
			int drawPoint = 24628 + (103 - viewportY) * 512 * 4;
			for (int viewportX = 1; viewportX < 103; viewportX++) {
				if ((currentSceneTileFlags[plane][viewportX][viewportY] & 0x18) == 0)
					currentScene.renderMinimapDot(pixels, drawPoint, 512, plane, viewportX, viewportY);
				if (plane < 3 && (currentSceneTileFlags[plane + 1][viewportX][viewportY] & 8) != 0)
					currentScene.renderMinimapDot(pixels, drawPoint, 512, plane + 1, viewportX, viewportY);
				drawPoint += 4;
			}

		}

		int primaryColour = ((238 + (int) (Math.random() * 20D)) - 10 << 16) + ((238 + (int) (Math.random() * 20D)) - 10 << 8)
				+ ((238 + (int) (Math.random() * 20D)) - 10);
		int secondaryColour = (238 + (int) (Math.random() * 20D)) - 10 << 16;
		minimapImage.createRasterizer();
		for (int viewportY = 1; viewportY < 103; viewportY++) {
			for (int viewportX = 1; viewportX < 103; viewportX++) {
				if ((currentSceneTileFlags[plane][viewportX][viewportY] & 0x18) == 0)
					method150(viewportY, plane, viewportX, secondaryColour, 563, primaryColour);
				if (plane < 3 && (currentSceneTileFlags[plane + 1][viewportX][viewportY] & 8) != 0)
					method150(viewportY, plane + 1, viewportX, secondaryColour, 563, primaryColour);
			}

		}

		if (aClass18_1158 != null) {
			aClass18_1158.createRasterizer();
			Rasterizer3D.lineOffsets = anIntArray1002;
		}
		anInt1082++;
		if (anInt1082 > 177) {
			anInt1082 = 0;
			outBuffer.putOpcode(173);
			outBuffer.putTriByte(0x288b80);
		}
		minimapHintCount = 0;
		for (int viewportX = 0; viewportX < 104; viewportX++) {
			for (int viewportY = 0; viewportY < 104; viewportY++) {
				int floorHash = currentScene.getFloorDecorationHash(this.plane, viewportX, viewportY);
				if (floorHash != 0) {
					floorHash = floorHash >> 14 & 0x7fff;
					int icon = GameObjectDefinition.getDefinition(floorHash).icon;
					if (icon >= 0) {
						int drawPointX = viewportX;
						int drawPointY = viewportY;
						if (icon != 22 && icon != 29 && icon != 34 && icon != 36 && icon != 46 && icon != 47 && icon != 48) {
							byte regionWidth = 104;
							byte regionHeight = 104;
							int flags[][] = currentCollisionMap[this.plane].adjacency;
							for (int off = 0; off < 10; off++) {
								int randPlane = (int) (Math.random() * 4D);
								if (randPlane == 0 && drawPointX > 0 && drawPointX > viewportX - 3 && (flags[drawPointX - 1][drawPointY] & 0x1280108) == 0)
									drawPointX--;
								if (randPlane == 1 && drawPointX < regionWidth - 1 && drawPointX < viewportX + 3 && (flags[drawPointX + 1][drawPointY] & 0x1280180) == 0)
									drawPointX++;
								if (randPlane == 2 && drawPointY > 0 && drawPointY > viewportY - 3 && (flags[drawPointX][drawPointY - 1] & 0x1280102) == 0)
									drawPointY--;
								if (randPlane == 3 && drawPointY < regionHeight - 1 && drawPointY < viewportY + 3 && (flags[drawPointX][drawPointY + 1] & 0x1280120) == 0)
									drawPointY++;
							}

						}
						minimapHint[minimapHintCount] = worldMapHintIcons[icon];
						minimapHintX[minimapHintCount] = drawPointX;
						minimapHintY[minimapHintCount] = drawPointY;
						minimapHintCount++;
					}
				}
			}

		}

	}

	public boolean method116(int j, byte[] abyte0) {
		if (abyte0 == null)
			return true;
		else
			return SignLink.saveWave(abyte0, j);
	}

	public int method117(byte byte0) {
		int i = 3;
		if (byte0 == aByte956)
			byte0 = 0;
		else
			startup();
		if (anInt1219 < 310) {
			anInt978++;
			if (anInt978 > 1457) {
				anInt978 = 0;
				outBuffer.putOpcode(244);
				outBuffer.putByte(0);
				int j = outBuffer.currentPosition;
				outBuffer.putByte(219);
				outBuffer.putShort(37745);
				outBuffer.putByte(61);
				outBuffer.putShort(43756);
				outBuffer.putShort((int) (Math.random() * 65536D));
				outBuffer.putByte((int) (Math.random() * 256D));
				outBuffer.putShort(51171);
				if ((int) (Math.random() * 2D) == 0)
					outBuffer.putShort(15808);
				outBuffer.putByte(97);
				outBuffer.putByte((int) (Math.random() * 256D));
				outBuffer.putLength(outBuffer.currentPosition - j);
			}
			int k = cameraX >> 7;
			int l = cameraY >> 7;
			int i1 = localPlayer.worldX >> 7;
			int j1 = localPlayer.worldY >> 7;
			if ((currentSceneTileFlags[plane][k][l] & 4) != 0)
				i = plane;
			int k1;
			if (i1 > k)
				k1 = i1 - k;
			else
				k1 = k - i1;
			int l1;
			if (j1 > l)
				l1 = j1 - l;
			else
				l1 = l - j1;
			if (k1 > l1) {
				int i2 = (l1 * 0x10000) / k1;
				int k2 = 32768;
				while (k != i1) {
					if (k < i1)
						k++;
					else if (k > i1)
						k--;
					if ((currentSceneTileFlags[plane][k][l] & 4) != 0)
						i = plane;
					k2 += i2;
					if (k2 >= 0x10000) {
						k2 -= 0x10000;
						if (l < j1)
							l++;
						else if (l > j1)
							l--;
						if ((currentSceneTileFlags[plane][k][l] & 4) != 0)
							i = plane;
					}
				}
			} else {
				int j2 = (k1 * 0x10000) / l1;
				int l2 = 32768;
				while (l != j1) {
					if (l < j1)
						l++;
					else if (l > j1)
						l--;
					if ((currentSceneTileFlags[plane][k][l] & 4) != 0)
						i = plane;
					l2 += j2;
					if (l2 >= 0x10000) {
						l2 -= 0x10000;
						if (k < i1)
							k++;
						else if (k > i1)
							k--;
						if ((currentSceneTileFlags[plane][k][l] & 4) != 0)
							i = plane;
					}
				}
			}
		}
		if ((currentSceneTileFlags[plane][localPlayer.worldX >> 7][localPlayer.worldY >> 7] & 4) != 0)
			i = plane;
		return i;
	}

	public int method118(int i) {
		int j = method110(cameraY, cameraX, (byte) 9, plane);
		while (i >= 0)
			opcode = buffer.getUnsignedByte();
		if (j - cameraZ < 800 && (currentSceneTileFlags[plane][cameraX >> 7][cameraY >> 7] & 4) != 0)
			return plane;
		else
			return 3;
	}

	public void startRunnable(Runnable runnable, int i) {
		if (i > 10)
			i = 10;
		if (SignLink.applet != null) {
			SignLink.startThread(runnable, i);
			return;
		} else {
			super.startRunnable(runnable, i);
			return;
		}
	}

	public void processPlayerAdditions(boolean priority) {
		if (localPlayer.worldX >> 7 == destinationX
				&& localPlayer.worldY >> 7 == destinationY)
			destinationX = 0;
		int count = localPlayerCount;
		if (priority)
			count = 1;
		for (int index = 0; index < count; index++) {
			Player player;
			int key;
			if (priority) {
				player = localPlayer;
				key = thisPlayerId << 14;
			} else {
				player = players[playerList[index]];
				key = playerList[index] << 14;
			}
			if (player == null || !player.isVisible())
				continue;
			player.aBoolean1763 = false;
			if ((lowMemory && localPlayerCount > 50 || localPlayerCount > 200)
					&& !priority
					&& player.movementAnimation == player.idleAnimation)
				player.aBoolean1763 = true;
			int viewportX = player.worldX >> 7;
			int viewportY = player.worldY >> 7;
			if (viewportX < 0 || viewportX >= 104 || viewportY < 0 || viewportY >= 104)
				continue;
			if (player.playerModel != null
					&& pulseCycle >= player.objectAppearanceStartTick
					&& pulseCycle < player.objectAppearanceEndTick) {
				player.aBoolean1763 = false;
				player.anInt1750 = method110(
						player.worldY,
						player.worldX, (byte) 9, plane);
				currentScene.addRenderable(player.anInt1750, player.anInt1769,
						60, 7, player, player.anInt1768,
						player.worldY, player.anInt1771,
						player.worldX,
						player.anInt1612, player.anInt1770,
						plane, key);
				continue;
			}
			if ((player.worldX & 0x7f) == 64
					&& (player.worldY & 0x7f) == 64) {
				if (anIntArrayArray886[viewportX][viewportY] == anInt1138)
					continue;
				anIntArrayArray886[viewportX][viewportY] = anInt1138;
			}
			player.anInt1750 = method110(player.worldY,
					player.worldX, (byte) 9, plane);
			currentScene.addEntity(key, player,
					player.worldX, player.anInt1750,
					player.aBoolean1592, 0, plane, 60,
					player.worldY,
					player.anInt1612);
		}
	}

	public void processMenuActions(int id) {
		if (id < 0)
			return;
		int first = firstMenuOperand[id];
		int second = secondMenuOperand[id];
		int action = menuActionTypes[id];
		int clicked = selectedMenuActions[id];
		if (action >= 2000)
			action -= 2000;
		if (inputType != 0 && action != 1016) {
			inputType = 0;
			redrawChatbox = true;
		}
		if (action == 200) {
			Player player = players[clicked];
			if (player != null) {
				walk(false, false, player.pathY[0],
						localPlayer.pathY[0], 1, 1, 2, 0,
						player.pathX[0], 0, 0,
						localPlayer.pathX[0]);
				anInt1020 = super.clickX;
				anInt1021 = super.clickY;
				crossType = 2;
				crossIndex = 0;
				outBuffer.putOpcode(245);
				outBuffer.putLEShortAdded(clicked);
			}
		}
		if (action == 227) {
			anInt1165++;
			if (anInt1165 >= 62) {
				outBuffer.putOpcode(165);
				outBuffer.putByte(206);
				anInt1165 = 0;
			}
			outBuffer.putOpcode(228);
			outBuffer.putLEShortDup(first);
			outBuffer.putShortAdded(clicked);
			outBuffer.putShort(second);
			atInventoryLoopCycle = 0;
			anInt1330 = second;
			anInt1331 = first;
			atInventoryInterfaceType = 2;
			if (Widget.forId(second).parentId == openInterfaceId)
				atInventoryInterfaceType = 1;
			if (Widget.forId(second).parentId == backDialogueId)
				atInventoryInterfaceType = 3;
		}
		if (action == 876) {
			Player player = players[clicked];
			if (player != null) {
				walk(false, false, player.pathY[0],
						localPlayer.pathY[0], 1, 1, 2, 0,
						player.pathX[0], 0, 0,
						localPlayer.pathX[0]);
				anInt1020 = super.clickX;
				anInt1021 = super.clickY;
				crossType = 2;
				crossIndex = 0;
				outBuffer.putOpcode(45);
				outBuffer.putShortAdded(clicked);
			}
		}
		if (action == 921) {
			Npc npc = npcs[clicked];
			if (npc != null) {
				walk(false, false, npc.pathY[0],
						localPlayer.pathY[0], 1, 1, 2, 0,
						npc.pathX[0], 0, 0,
						localPlayer.pathX[0]);
				anInt1020 = super.clickX;
				anInt1021 = super.clickY;
				crossType = 2;
				crossIndex = 0;
				outBuffer.putOpcode(67);
				outBuffer.putShortAdded(clicked);
			}
		}
		if (action == 961) {
			anInt1139 += clicked;
			if (anInt1139 >= 115) {
				outBuffer.putOpcode(126);
				outBuffer.putByte(125);
				anInt1139 = 0;
			}
			outBuffer.putOpcode(203);
			outBuffer.putShortAdded(second);
			outBuffer.putLEShortDup(first);
			outBuffer.putLEShortDup(clicked);
			atInventoryLoopCycle = 0;
			anInt1330 = second;
			anInt1331 = first;
			atInventoryInterfaceType = 2;
			if (Widget.forId(second).parentId == openInterfaceId)
				atInventoryInterfaceType = 1;
			if (Widget.forId(second).parentId == backDialogueId)
				atInventoryInterfaceType = 3;
		}
		if (action == 467 && method80(second, 0, first, clicked)) {
			outBuffer.putOpcode(152);
			outBuffer.putLEShortDup(clicked >> 14 & 0x7fff);
			outBuffer.putLEShortDup(anInt1148);
			outBuffer.putLEShortDup(anInt1149);
			outBuffer.putLEShortDup(second + nextTopRightTileY);
			outBuffer.putShort(anInt1147);
			outBuffer.putLEShortAdded(first + nextTopLeftTileX);
		}
		if (action == 9) {
			outBuffer.putOpcode(3);
			outBuffer.putShortAdded(clicked);
			outBuffer.putShort(second);
			outBuffer.putShort(first);
			atInventoryLoopCycle = 0;
			anInt1330 = second;
			anInt1331 = first;
			atInventoryInterfaceType = 2;
			if (Widget.forId(second).parentId == openInterfaceId)
				atInventoryInterfaceType = 1;
			if (Widget.forId(second).parentId == backDialogueId)
				atInventoryInterfaceType = 3;
		}
		if (action == 553) {
			Npc npc = npcs[clicked];
			if (npc != null) {
				walk(false, false, npc.pathY[0],
						localPlayer.pathY[0], 1, 1, 2, 0,
						npc.pathX[0], 0, 0,
						localPlayer.pathX[0]);
				anInt1020 = super.clickX;
				anInt1021 = super.clickY;
				crossType = 2;
				crossIndex = 0;
				outBuffer.putOpcode(42);
				outBuffer.putLEShortDup(clicked);
			}
		}
		if (action == 677) {
			Player player = players[clicked];
			if (player != null) {
				walk(false, false, player.pathY[0],
						localPlayer.pathY[0], 1, 1, 2, 0,
						player.pathX[0], 0, 0,
						localPlayer.pathX[0]);
				anInt1020 = super.clickX;
				anInt1021 = super.clickY;
				crossType = 2;
				crossIndex = 0;
				outBuffer.putOpcode(116);
				outBuffer.putLEShortDup(clicked);
			}
		}
		if (action == Actions.ADD_FRIEND ||
				action == Actions.ADD_IGNORE ||
				action == Actions.REMOVE_FRIEND ||
				action == Actions.REMOVE_IGNORE) {
			String s = menuActionTexts[id];
			int l1 = s.indexOf("@whi@");
			if (l1 != -1) {
				long l3 = TextUtils.nameToLong(s.substring(l1 + 5).trim());
				if (action == Actions.ADD_FRIEND)
					addFriend(l3);
				if (action == Actions.ADD_IGNORE)
					addIgnore(anInt1154, l3);
				if (action == Actions.REMOVE_FRIEND)
					removeFriend(l3);
				if (action == Actions.REMOVE_IGNORE)
					removeIgnore(325, l3);
			}
		}
		if (action == 930) {
			boolean flag = walk(false, false, second, localPlayer.pathY[0], 0, 0, 2, 0, first, 0, 0,
					localPlayer.pathX[0]);
			if (!flag)
				flag = walk(false, false, second, localPlayer.pathY[0], 1, 1, 2, 0, first, 0, 0,
						localPlayer.pathX[0]);
			anInt1020 = super.clickX;
			anInt1021 = super.clickY;
			crossType = 2;
			crossIndex = 0;
			outBuffer.putOpcode(54);
			outBuffer.putShortAdded(clicked);
			outBuffer.putLEShortDup(second + nextTopRightTileY);
			outBuffer.putShort(first + nextTopLeftTileX);
		}
		if (action == 399) {
			outBuffer.putOpcode(24);
			outBuffer.putLEShortDup(second);
			outBuffer.putLEShortDup(clicked);
			outBuffer.putShortAdded(first);
			atInventoryLoopCycle = 0;
			anInt1330 = second;
			anInt1331 = first;
			atInventoryInterfaceType = 2;
			if (Widget.forId(second).parentId == openInterfaceId)
				atInventoryInterfaceType = 1;
			if (Widget.forId(second).parentId == backDialogueId)
				atInventoryInterfaceType = 3;
		}
		if (action == 347) {
			Npc class50_sub1_sub4_sub3_sub1_2 = npcs[clicked];
			if (class50_sub1_sub4_sub3_sub1_2 != null) {
				walk(false, false, class50_sub1_sub4_sub3_sub1_2.pathY[0],
						localPlayer.pathY[0], 1, 1, 2, 0,
						class50_sub1_sub4_sub3_sub1_2.pathX[0], 0, 0,
						localPlayer.pathX[0]);
				anInt1020 = super.clickX;
				anInt1021 = super.clickY;
				crossType = 2;
				crossIndex = 0;
				outBuffer.putOpcode(57);
				outBuffer.putShort(clicked);
				outBuffer.putLEShortDup(anInt1149);
				outBuffer.putLEShortAdded(anInt1148);
				outBuffer.putShort(anInt1147);
			}
		}
		if (action == Actions.TOGGLE_SETTING_WIDGET) {
			outBuffer.putOpcode(79);
			outBuffer.putShort(second);
			Widget widget = Widget.forId(second);
			if (widget.opcodes != null && widget.opcodes[0][0] == 5) {
				int setting = widget.opcodes[0][1];
				widgetSettings[setting] = 1 - widgetSettings[setting];
				updateVarp(0, setting);
				redrawTabArea = true;
			}
		}
		if (action == 493) {
			Player class50_sub1_sub4_sub3_sub2_3 = players[clicked];
			if (class50_sub1_sub4_sub3_sub2_3 != null) {
				walk(false, false, class50_sub1_sub4_sub3_sub2_3.pathY[0],
						localPlayer.pathY[0], 1, 1, 2, 0,
						class50_sub1_sub4_sub3_sub2_3.pathX[0], 0, 0,
						localPlayer.pathX[0]);
				anInt1020 = super.clickX;
				anInt1021 = super.clickY;
				crossType = 2;
				crossIndex = 0;
				outBuffer.putOpcode(233);
				outBuffer.putShortAdded(clicked);
			}
		}
		if (action == 14)
			if (!menuOpen)
				currentScene.method279(0, super.clickX - 4, super.clickY - 4);
			else
				currentScene.method279(0, first - 4, second - 4);
		if (action == 903) {
			outBuffer.putOpcode(1);
			outBuffer.putShort(clicked);
			outBuffer.putLEShortDup(anInt1147);
			outBuffer.putLEShortDup(anInt1149);
			outBuffer.putLEShortAdded(anInt1148);
			outBuffer.putShortAdded(first);
			outBuffer.putShortAdded(second);
			atInventoryLoopCycle = 0;
			anInt1330 = second;
			anInt1331 = first;
			atInventoryInterfaceType = 2;
			if (Widget.forId(second).parentId == openInterfaceId)
				atInventoryInterfaceType = 1;
			if (Widget.forId(second).parentId == backDialogueId)
				atInventoryInterfaceType = 3;
		}
		if (action == 361) {
			outBuffer.putOpcode(36);
			outBuffer.putShort(anInt1172);
			outBuffer.putShortAdded(second);
			outBuffer.putShortAdded(first);
			outBuffer.putShortAdded(clicked);
			atInventoryLoopCycle = 0;
			anInt1330 = second;
			anInt1331 = first;
			atInventoryInterfaceType = 2;
			if (Widget.forId(second).parentId == openInterfaceId)
				atInventoryInterfaceType = 1;
			if (Widget.forId(second).parentId == backDialogueId)
				atInventoryInterfaceType = 3;
		}
		if (action == 118) {
			Npc class50_sub1_sub4_sub3_sub1_3 = npcs[clicked];
			if (class50_sub1_sub4_sub3_sub1_3 != null) {
				walk(false, false, class50_sub1_sub4_sub3_sub1_3.pathY[0],
						localPlayer.pathY[0], 1, 1, 2, 0,
						class50_sub1_sub4_sub3_sub1_3.pathX[0], 0, 0,
						localPlayer.pathX[0]);
				anInt1020 = super.clickX;
				anInt1021 = super.clickY;
				crossType = 2;
				crossIndex = 0;
				anInt1235 += clicked;
				if (anInt1235 >= 143) {
					outBuffer.putOpcode(157);
					outBuffer.putInt(0);
					anInt1235 = 0;
				}
				outBuffer.putOpcode(13);
				outBuffer.putLEShortAdded(clicked);
			}
		}
		if (action == 376 && method80(second, 0, first, clicked)) {
			outBuffer.putOpcode(210);
			outBuffer.putShort(anInt1172);
			outBuffer.putLEShortDup(clicked >> 14 & 0x7fff);
			outBuffer.putShortAdded(first + nextTopLeftTileX);
			outBuffer.putLEShortDup(second + nextTopRightTileY);
		}
		if (action == 432) {
			Npc class50_sub1_sub4_sub3_sub1_4 = npcs[clicked];
			if (class50_sub1_sub4_sub3_sub1_4 != null) {
				walk(false, false, class50_sub1_sub4_sub3_sub1_4.pathY[0],
						localPlayer.pathY[0], 1, 1, 2, 0,
						class50_sub1_sub4_sub3_sub1_4.pathX[0], 0, 0,
						localPlayer.pathX[0]);
				anInt1020 = super.clickX;
				anInt1021 = super.clickY;
				crossType = 2;
				crossIndex = 0;
				outBuffer.putOpcode(8);
				outBuffer.putLEShortDup(clicked);
			}
		}
		if (action == Actions.CLOSE_WIDGETS)
			closeWidgets();
		if (action == 918) {
			Player class50_sub1_sub4_sub3_sub2_4 = players[clicked];
			if (class50_sub1_sub4_sub3_sub2_4 != null) {
				walk(false, false, class50_sub1_sub4_sub3_sub2_4.pathY[0],
						localPlayer.pathY[0], 1, 1, 2, 0,
						class50_sub1_sub4_sub3_sub2_4.pathX[0], 0, 0,
						localPlayer.pathX[0]);
				anInt1020 = super.clickX;
				anInt1021 = super.clickY;
				crossType = 2;
				crossIndex = 0;
				outBuffer.putOpcode(31);
				outBuffer.putShort(clicked);
				outBuffer.putLEShortDup(anInt1172);
			}
		}
		if (action == 67) {
			Npc class50_sub1_sub4_sub3_sub1_5 = npcs[clicked];
			if (class50_sub1_sub4_sub3_sub1_5 != null) {
				walk(false, false, class50_sub1_sub4_sub3_sub1_5.pathY[0],
						localPlayer.pathY[0], 1, 1, 2, 0,
						class50_sub1_sub4_sub3_sub1_5.pathX[0], 0, 0,
						localPlayer.pathX[0]);
				anInt1020 = super.clickX;
				anInt1021 = super.clickY;
				crossType = 2;
				crossIndex = 0;
				outBuffer.putOpcode(104);
				outBuffer.putShortAdded(anInt1172);
				outBuffer.putLEShortDup(clicked);
			}
		}
		if (action == 68) {
			boolean flag1 = walk(false, false, second, localPlayer.pathY[0], 0, 0, 2, 0, first, 0, 0,
					localPlayer.pathX[0]);
			if (!flag1)
				flag1 = walk(false, false, second, localPlayer.pathY[0], 1, 1, 2, 0, first, 0, 0,
						localPlayer.pathX[0]);
			anInt1020 = super.clickX;
			anInt1021 = super.clickY;
			crossType = 2;
			crossIndex = 0;
			outBuffer.putOpcode(77);
			outBuffer.putShortAdded(first + nextTopLeftTileX);
			outBuffer.putShort(second + nextTopRightTileY);
			outBuffer.putLEShortAdded(clicked);
		}
		if (action == 684) {
			boolean flag2 = walk(false, false, second, localPlayer.pathY[0], 0, 0, 2, 0, first, 0, 0,
					localPlayer.pathX[0]);
			if (!flag2)
				flag2 = walk(false, false, second, localPlayer.pathY[0], 1, 1, 2, 0, first, 0, 0,
						localPlayer.pathX[0]);
			anInt1020 = super.clickX;
			anInt1021 = super.clickY;
			crossType = 2;
			crossIndex = 0;
			if ((clicked & 3) == 0)
				anInt1052++;
			if (anInt1052 >= 84) {
				outBuffer.putOpcode(222);
				outBuffer.putTriByte(0xabc842);
				anInt1052 = 0;
			}
			outBuffer.putOpcode(71);
			outBuffer.putLEShortAdded(clicked);
			outBuffer.putLEShortAdded(first + nextTopLeftTileX);
			outBuffer.putShortAdded(second + nextTopRightTileY);
		}
		if (action == Actions.ACCEPT_TRADE || action == Actions.ACCEPT_CHALLENGE) {
			String name = menuActionTexts[id];
			int colour = name.indexOf("@whi@");
			if (colour != -1) {
				name = name.substring(colour + 5).trim();
				String username = TextUtils.formatName(TextUtils.longToName(TextUtils.nameToLong(name)));
				boolean found = false;
				for (int index = 0; index < localPlayerCount; index++) {
					Player player = players[playerList[index]];
					if (player == null || player.playerName == null
							|| !player.playerName.equalsIgnoreCase(username))
						continue;
					walk(false, false, player.pathY[0],
							localPlayer.pathY[0], 1, 1, 2, 0,
							player.pathX[0], 0, 0,
							localPlayer.pathX[0]);
					if (action == Actions.ACCEPT_TRADE) {
						outBuffer.putOpcode(116);
						outBuffer.putLEShortDup(playerList[index]);
					}
					if (action == Actions.ACCEPT_CHALLENGE) {
						outBuffer.putOpcode(245);
						outBuffer.putLEShortAdded(playerList[index]);
					}
					found = true;
					break;
				}

				if (!found)
					addChatMessage("", "Unable to find " + username, 0);
			}
		}
		if (action == 225) {
			outBuffer.putOpcode(177); // second item action
			outBuffer.putShortAdded(first);
			outBuffer.putLEShortDup(clicked);
			outBuffer.putLEShortDup(second);
			atInventoryLoopCycle = 0;
			anInt1330 = second;
			anInt1331 = first;
			atInventoryInterfaceType = 2;
			if (Widget.forId(second).parentId == openInterfaceId)
				atInventoryInterfaceType = 1;
			if (Widget.forId(second).parentId == backDialogueId)
				atInventoryInterfaceType = 3;
		}
		if (action == Actions.USABLE_WIDGET) {
			Widget widget = Widget.forId(second);
			widgetSelected = 1;
			anInt1172 = second;
			anInt1173 = widget.optionAttributes;
			itemSelected = 0;
			redrawTabArea = true;
			String prefix = widget.optionCircumfix;
			if (prefix.indexOf(" ") != -1)
				prefix = prefix.substring(0, prefix.indexOf(" "));
			String suffix = widget.optionCircumfix;
			if (suffix.indexOf(" ") != -1)
				suffix = suffix.substring(suffix.indexOf(" ") + 1);
			selectedWidgetName = prefix + " " + widget.optionText + " " + suffix;
			if (anInt1173 == 16) {
				redrawTabArea = true;
				anInt1285 = 3;
				aBoolean950 = true;
			}
			return;
		}
		if (action == 891) {
			outBuffer.putOpcode(4);
			outBuffer.putLEShortDup(first);
			outBuffer.putLEShortAdded(clicked);
			outBuffer.putLEShortAdded(second);
			atInventoryLoopCycle = 0;
			anInt1330 = second;
			anInt1331 = first;
			atInventoryInterfaceType = 2;
			if (Widget.forId(second).parentId == openInterfaceId)
				atInventoryInterfaceType = 1;
			if (Widget.forId(second).parentId == backDialogueId)
				atInventoryInterfaceType = 3;
		}
		if (action == 894) {
			outBuffer.putOpcode(158); // fifth item action event
			outBuffer.putLEShortAdded(first);
			outBuffer.putLEShortAdded(clicked);
			outBuffer.putLEShortDup(second);
			atInventoryLoopCycle = 0;
			anInt1330 = second;
			anInt1331 = first;
			atInventoryInterfaceType = 2;
			if (Widget.forId(second).parentId == openInterfaceId)
				atInventoryInterfaceType = 1;
			if (Widget.forId(second).parentId == backDialogueId)
				atInventoryInterfaceType = 3;
		}
		if (action == 1280) {
			method80(second, 0, first, clicked);
			outBuffer.putOpcode(55);
			outBuffer.putLEShortDup(clicked >> 14 & 0x7fff);
			outBuffer.putLEShortDup(second + nextTopRightTileY);
			outBuffer.putShort(first + nextTopLeftTileX);
		}
		if (action == 35) {
			method80(second, 0, first, clicked);
			outBuffer.putOpcode(181);
			outBuffer.putShortAdded(first + nextTopLeftTileX);
			outBuffer.putLEShortDup(second + nextTopRightTileY);
			outBuffer.putLEShortDup(clicked >> 14 & 0x7fff);
		}
		if (action == 888) {
			method80(second, 0, first, clicked);
			outBuffer.putOpcode(50);
			outBuffer.putShortAdded(second + nextTopRightTileY);
			outBuffer.putLEShortDup(clicked >> 14 & 0x7fff);
			outBuffer.putLEShortAdded(first + nextTopLeftTileX);
		}
		if (action == 324) {
			outBuffer.putOpcode(161);
			outBuffer.putLEShortAdded(first);
			outBuffer.putLEShortAdded(clicked);
			outBuffer.putLEShortDup(second);
			atInventoryLoopCycle = 0;
			anInt1330 = second;
			anInt1331 = first;
			atInventoryInterfaceType = 2;
			if (Widget.forId(second).parentId == openInterfaceId)
				atInventoryInterfaceType = 1;
			if (Widget.forId(second).parentId == backDialogueId)
				atInventoryInterfaceType = 3;
		}
		if (action == Actions.EXAMINE_ITEM) {
			ItemDefinition definition = ItemDefinition.lookup(clicked);
			Widget widget = Widget.forId(second);
			String description;
			if (widget != null && widget.itemAmounts[first] >= 0x186a0)
				description = widget.itemAmounts[first] + " x " + definition.name;
			else if (definition.description != null)
				description = new String(definition.description);
			else
				description = "It's a " + definition.name + ".";
			addChatMessage("", description, 0);
		}
		if (action == 352) {
			Widget class13_2 = Widget.forId(second);
			boolean flag7 = true;
			if (class13_2.contentType > 0)
				flag7 = handleWidgetDynamicAction(class13_2);
			if (flag7) {
				outBuffer.putOpcode(79);
				outBuffer.putShort(second);
			}
		}
		if (action == 1412) {
			int k1 = clicked >> 14 & 0x7fff;
			GameObjectDefinition class47 = GameObjectDefinition.getDefinition(k1);
			String s9;
			if (class47.description != null)
				s9 = new String(class47.description);
			else
				s9 = "It's a " + class47.name + ".";
			addChatMessage("", s9, 0);
		}
		if (action == 575 && !aBoolean1239) {
			outBuffer.putOpcode(226);
			outBuffer.putShort(second);
			aBoolean1239 = true;
		}
		if (action == 892) {
			method80(second, 0, first, clicked);
			outBuffer.putOpcode(136);
			outBuffer.putShort(first + nextTopLeftTileX);
			outBuffer.putLEShortDup(second + nextTopRightTileY);
			outBuffer.putShort(clicked >> 14 & 0x7fff);
		}
		if (action == 270) {
			boolean flag3 = walk(false, false, second, localPlayer.pathY[0], 0, 0, 2, 0, first, 0, 0,
					localPlayer.pathX[0]);
			if (!flag3)
				flag3 = walk(false, false, second, localPlayer.pathY[0], 1, 1, 2, 0, first, 0, 0,
						localPlayer.pathX[0]);
			anInt1020 = super.clickX;
			anInt1021 = super.clickY;
			crossType = 2;
			crossIndex = 0;
			outBuffer.putOpcode(230);
			outBuffer.putLEShortDup(clicked);
			outBuffer.putShortAdded(first + nextTopLeftTileX);
			outBuffer.putShort(second + nextTopRightTileY);
		}
		if (action == 596) {
			Player class50_sub1_sub4_sub3_sub2_5 = players[clicked];
			if (class50_sub1_sub4_sub3_sub2_5 != null) {
				walk(false, false, class50_sub1_sub4_sub3_sub2_5.pathY[0],
						localPlayer.pathY[0], 1, 1, 2, 0,
						class50_sub1_sub4_sub3_sub2_5.pathX[0], 0, 0,
						localPlayer.pathX[0]);
				anInt1020 = super.clickX;
				anInt1021 = super.clickY;
				crossType = 2;
				crossIndex = 0;
				outBuffer.putOpcode(143);
				outBuffer.putLEShortDup(anInt1149);
				outBuffer.putLEShortAdded(anInt1147);
				outBuffer.putShort(anInt1148);
				outBuffer.putShortAdded(clicked);
			}
		}
		if (action == 100) {
			boolean flag4 = walk(false, false, second, localPlayer.pathY[0], 0, 0, 2, 0, first, 0, 0,
					localPlayer.pathX[0]);
			if (!flag4)
				flag4 = walk(false, false, second, localPlayer.pathY[0], 1, 1, 2, 0, first, 0, 0,
						localPlayer.pathX[0]);
			anInt1020 = super.clickX;
			anInt1021 = super.clickY;
			crossType = 2;
			crossIndex = 0;
			outBuffer.putOpcode(211);
			outBuffer.putLEShortAdded(anInt1147);
			outBuffer.putShortAdded(anInt1149);
			outBuffer.putLEShortAdded(second + nextTopRightTileY);
			outBuffer.putLEShortAdded(first + nextTopLeftTileX);
			outBuffer.putLEShortDup(anInt1148);
			outBuffer.putLEShortDup(clicked);
		}
		if (action == 1668) {
			Npc class50_sub1_sub4_sub3_sub1_6 = npcs[clicked];
			if (class50_sub1_sub4_sub3_sub1_6 != null) {
				ActorDefinition class37 = class50_sub1_sub4_sub3_sub1_6.npcDefinition;
				if (class37.childrenIds != null)
					class37 = class37.getChildDefinition();
				if (class37 != null) {
					String s10;
					if (class37.description != null)
						s10 = new String(class37.description);
					else
						s10 = "It's a " + class37.name + ".";
					addChatMessage("", s10, 0);
				}
			}
		}
		if (action == 26) {
			boolean flag5 = walk(false, false, second, localPlayer.pathY[0], 0, 0, 2, 0, first, 0, 0,
					localPlayer.pathX[0]);
			if (!flag5)
				flag5 = walk(false, false, second, localPlayer.pathY[0], 1, 1, 2, 0, first, 0, 0,
						localPlayer.pathX[0]);
			anInt1020 = super.clickX;
			anInt1021 = super.clickY;
			crossType = 2;
			crossIndex = 0;
			anInt1100++;
			if (anInt1100 >= 120) {
				outBuffer.putOpcode(95);
				outBuffer.putInt(0);
				anInt1100 = 0;
			}
			outBuffer.putOpcode(100);
			outBuffer.putShort(first + nextTopLeftTileX);
			outBuffer.putShortAdded(second + nextTopRightTileY);
			outBuffer.putLEShortAdded(clicked);
		}
		if (action == 444) {
			outBuffer.putOpcode(91); // third item action
			outBuffer.putLEShortDup(clicked);
			outBuffer.putLEShortAdded(first);
			outBuffer.putShort(second);
			atInventoryLoopCycle = 0;
			anInt1330 = second;
			anInt1331 = first;
			atInventoryInterfaceType = 2;
			if (Widget.forId(second).parentId == openInterfaceId)
				atInventoryInterfaceType = 1;
			if (Widget.forId(second).parentId == backDialogueId)
				atInventoryInterfaceType = 3;
		}
		if (action == 507) {
			String string = menuActionTexts[id];
			int i_389_ = string.indexOf("@whi@");
			if (i_389_ != -1)
				if (openInterfaceId == -1) {
					closeWidgets();
					reportedName = string.substring(i_389_ + 5).trim();
					reportMutePlayer = false;
					reportAbuseInterfaceID = openInterfaceId = Widget.anInt246;
				} else {
					addChatMessage("", "Please close the interface you have open before using 'report abuse'", 0);
				}
		}
		if (action == 389) {
			method80(second, 0, first, clicked);
			outBuffer.putOpcode(241);
			outBuffer.putShort(clicked >> 14 & 0x7fff);
			outBuffer.putShort(first + nextTopLeftTileX);
			outBuffer.putShortAdded(second + nextTopRightTileY);
		}
		if (action == 564) {
			outBuffer.putOpcode(231); // fourth item action event
			outBuffer.putLEShortAdded(second);
			outBuffer.putLEShortDup(first);
			outBuffer.putShort(clicked);
			atInventoryLoopCycle = 0;
			anInt1330 = second;
			anInt1331 = first;
			atInventoryInterfaceType = 2;
			if (Widget.forId(second).parentId == openInterfaceId)
				atInventoryInterfaceType = 1;
			if (Widget.forId(second).parentId == backDialogueId)
				atInventoryInterfaceType = 3;
		}
		if (action == 984) {
			String s3 = menuActionTexts[id];
			int l2 = s3.indexOf("@whi@");
			if (l2 != -1) {
				long l4 = TextUtils.nameToLong(s3.substring(l2 + 5).trim());
				int k3 = -1;
				for (int i4 = 0; i4 < friendsCount; i4++) {
					if (friends[i4] != l4)
						continue;
					k3 = i4;
					break;
				}

				if (k3 != -1 && friendWorlds[k3] > 0) {
					redrawChatbox = true;
					inputType = 0;
					messagePromptRaised = true;
					chatMessage = "";
					friendsListAction = 3;
					aLong1141 = friends[k3];
					chatboxInputMessage = "Enter message to send to " + friendUsernames[k3];
				}
			}
		}
		if (action == Actions.RESET_SETTING_WIDGET) {
			outBuffer.putOpcode(79);
			outBuffer.putShort(second);
			Widget widget = Widget.forId(second);
			if (widget.opcodes != null && widget.opcodes[0][0] == 5) {
				int operand = widget.opcodes[0][1];
				if (widgetSettings[operand] != widget.conditionValues[0]) {
					widgetSettings[operand] = widget.conditionValues[0];
					updateVarp(0, operand);
					redrawTabArea = true;
				}
			}
		}
		if (action == 318) {
			Npc class50_sub1_sub4_sub3_sub1_7 = npcs[clicked];
			if (class50_sub1_sub4_sub3_sub1_7 != null) {
				walk(false, false, class50_sub1_sub4_sub3_sub1_7.pathY[0],
						localPlayer.pathY[0], 1, 1, 2, 0,
						class50_sub1_sub4_sub3_sub1_7.pathX[0], 0, 0,
						localPlayer.pathX[0]);
				anInt1020 = super.clickX;
				anInt1021 = super.clickY;
				crossType = 2;
				crossIndex = 0;
				outBuffer.putOpcode(112);
				outBuffer.putLEShortDup(clicked);
			}
		}
		if (action == 199) {
			boolean flag6 = walk(false, false, second, localPlayer.pathY[0], 0, 0, 2, 0, first, 0, 0,
					localPlayer.pathX[0]);
			if (!flag6)
				flag6 = walk(false, false, second, localPlayer.pathY[0], 1, 1, 2, 0, first, 0, 0,
						localPlayer.pathX[0]);
			anInt1020 = super.clickX;
			anInt1021 = super.clickY;
			crossType = 2;
			crossIndex = 0;
			outBuffer.putOpcode(83);
			outBuffer.putLEShortDup(clicked);
			outBuffer.putShort(second + nextTopRightTileY);
			outBuffer.putLEShortDup(anInt1172);
			outBuffer.putLEShortAdded(first + nextTopLeftTileX);
		}
		if (action == 55) {
			method44(aBoolean1190, dialogueId);
			dialogueId = -1;
			redrawChatbox = true;
		}
		if (action == 52) {
			itemSelected = 1;
			anInt1147 = first;
			anInt1148 = second;
			anInt1149 = clicked;
			aString1150 = String.valueOf(ItemDefinition.lookup(clicked).name);
			widgetSelected = 0;
			redrawTabArea = true;
			return;
		}
		if (action == 1564) {
			ItemDefinition class16_1 = ItemDefinition.lookup(clicked);
			String s6;
			if (class16_1.description != null)
				s6 = new String(class16_1.description);
			else
				s6 = "It's a " + class16_1.name + ".";
			addChatMessage("", s6, 0);
		}
		if (action == 408) {
			Player class50_sub1_sub4_sub3_sub2_6 = players[clicked];
			if (class50_sub1_sub4_sub3_sub2_6 != null) {
				walk(false, false, class50_sub1_sub4_sub3_sub2_6.pathY[0],
						localPlayer.pathY[0], 1, 1, 2, 0,
						class50_sub1_sub4_sub3_sub2_6.pathX[0], 0, 0,
						localPlayer.pathX[0]);
				anInt1020 = super.clickX;
				anInt1021 = super.clickY;
				crossType = 2;
				crossIndex = 0;
				outBuffer.putOpcode(194);
				outBuffer.putLEShortDup(clicked);
			}
		}
		itemSelected = 0;
		widgetSelected = 0;
		redrawTabArea = true;
	}

	public void method121(boolean flag) {
		anInt939 = 0;
		for (int i = -1; i < localPlayerCount + anInt1133; i++) {
			Object obj;
			if (i == -1)
				obj = localPlayer;
			else if (i < localPlayerCount)
				obj = players[playerList[i]];
			else
				obj = npcs[anIntArray1134[i - localPlayerCount]];
			if (obj == null || !((Actor) (obj)).isVisible())
				continue;
			if (obj instanceof Npc) {
				ActorDefinition class37 = ((Npc) obj).npcDefinition;
				if (class37.childrenIds != null)
					class37 = class37.getChildDefinition();
				if (class37 == null)
					continue;
			}
			if (i < localPlayerCount) {
				int k = 30;
				Player class50_sub1_sub4_sub3_sub2 = (Player) obj;
				if (class50_sub1_sub4_sub3_sub2.anInt1756 != -1 || class50_sub1_sub4_sub3_sub2.anInt1748 != -1) {
					method136(((Actor) (obj)), false, ((Actor) (obj)).modelHeight + 15);
					if (anInt932 > -1) {
						if (class50_sub1_sub4_sub3_sub2.anInt1756 != -1) {
							aClass50_Sub1_Sub1_Sub1Array1288[class50_sub1_sub4_sub3_sub2.anInt1756].drawImage(anInt933
									- k, anInt932 - 12);
							k += 25;
						}
						if (class50_sub1_sub4_sub3_sub2.anInt1748 != -1) {
							aClass50_Sub1_Sub1_Sub1Array1079[class50_sub1_sub4_sub3_sub2.anInt1748].drawImage(anInt933
									- k, anInt932 - 12);
							k += 25;
						}
					}
				}
				if (i >= 0 && anInt1197 == 10 && anInt1151 == playerList[i]) {
					method136(((Actor) (obj)), false, ((Actor) (obj)).modelHeight + 15);
					if (anInt932 > -1)
						aClass50_Sub1_Sub1_Sub1Array954[1].drawImage(anInt933 - k, anInt932 - 12);
				}
			} else {
				ActorDefinition class37_1 = ((Npc) obj).npcDefinition;
				if (class37_1.headIcon >= 0 && class37_1.headIcon < aClass50_Sub1_Sub1_Sub1Array1079.length) {
					method136(((Actor) (obj)), false, ((Actor) (obj)).modelHeight + 15);
					if (anInt932 > -1)
						aClass50_Sub1_Sub1_Sub1Array1079[class37_1.headIcon].drawImage(anInt933 - 30, anInt932 - 12
						);
				}
				if (anInt1197 == 1 && anInt1226 == anIntArray1134[i - localPlayerCount] && pulseCycle % 20 < 10) {
					method136(((Actor) (obj)), false, ((Actor) (obj)).modelHeight + 15);
					if (anInt932 > -1)
						aClass50_Sub1_Sub1_Sub1Array954[0].drawImage(anInt933 - 28, anInt932 - 12);
				}
			}
			if (((Actor) (obj)).forcedChat != null
					&& (i >= localPlayerCount || publicChatMode == 0 || publicChatMode == 3 || publicChatMode == 1
							&& method148(13292, ((Player) obj).playerName))) {
				method136(((Actor) (obj)), false, ((Actor) (obj)).modelHeight);
				if (anInt932 > -1 && anInt939 < anInt940) {
					anIntArray944[anInt939] = fontBold.getStringWidth(((Actor) (obj)).forcedChat
					) / 2;
					anIntArray943[anInt939] = fontBold.characterDefaultHeight;
					anIntArray941[anInt939] = anInt932;
					anIntArray942[anInt939] = anInt933;
					anIntArray945[anInt939] = ((Actor) (obj)).textColour;
					anIntArray946[anInt939] = ((Actor) (obj)).textEffect;
					anIntArray947[anInt939] = ((Actor) (obj)).textCycle;
					aStringArray948[anInt939++] = ((Actor) (obj)).forcedChat;
					if (anInt998 == 0 && ((Actor) (obj)).textEffect >= 1 && ((Actor) (obj)).textEffect <= 3) {
						anIntArray943[anInt939] += 10;
						anIntArray942[anInt939] += 5;
					}
					if (anInt998 == 0 && ((Actor) (obj)).textEffect == 4)
						anIntArray944[anInt939] = 60;
					if (anInt998 == 0 && ((Actor) (obj)).textEffect == 5)
						anIntArray943[anInt939] += 5;
				}
			}
			if (((Actor) (obj)).endCycle > pulseCycle) {
				method136(((Actor) (obj)), false, ((Actor) (obj)).modelHeight + 15);
				if (anInt932 > -1) {
					int l = (((Actor) (obj)).anInt1596 * 30) / ((Actor) (obj)).anInt1597;
					if (l > 30)
						l = 30;
					Rasterizer.drawFilledRectangle(anInt932 - 15, anInt933 - 3, l, 5, 65280);
					Rasterizer.drawFilledRectangle((anInt932 - 15) + l, anInt933 - 3, 30 - l, 5, 0xff0000);
				}
			}
			for (int i1 = 0; i1 < 4; i1++)
				if (((Actor) (obj)).hitCycles[i1] > pulseCycle) {
					method136(((Actor) (obj)), false, ((Actor) (obj)).modelHeight / 2);
					if (anInt932 > -1) {
						if (i1 == 1)
							anInt933 -= 20;
						if (i1 == 2) {
							anInt932 -= 15;
							anInt933 -= 10;
						}
						if (i1 == 3) {
							anInt932 += 15;
							anInt933 -= 10;
						}
						aClass50_Sub1_Sub1_Sub1Array1182[((Actor) (obj)).hitTypes[i1]].drawImage(
								anInt933 - 12, anInt932 - 12);
						fontSmall.drawStringLeft(String
								.valueOf(((Actor) (obj)).hitDamages[i1]), anInt932, anInt933 + 4, 0);
						fontSmall.drawStringLeft(String
								.valueOf(((Actor) (obj)).hitDamages[i1]), anInt932 - 1, anInt933 + 3, 0xffffff);
					}
				}

		}

		for (int j = 0; j < anInt939; j++) {
			int j1 = anIntArray941[j];
			int k1 = anIntArray942[j];
			int l1 = anIntArray944[j];
			int i2 = anIntArray943[j];
			boolean flag1 = true;
			while (flag1) {
				flag1 = false;
				for (int j2 = 0; j2 < j; j2++)
					if (k1 + 2 > anIntArray942[j2] - anIntArray943[j2] && k1 - i2 < anIntArray942[j2] + 2
							&& j1 - l1 < anIntArray941[j2] + anIntArray944[j2]
							&& j1 + l1 > anIntArray941[j2] - anIntArray944[j2]
							&& anIntArray942[j2] - anIntArray943[j2] < k1) {
						k1 = anIntArray942[j2] - anIntArray943[j2];
						flag1 = true;
					}

			}
			anInt932 = anIntArray941[j];
			anInt933 = anIntArray942[j] = k1;
			String s = aStringArray948[j];
			if (anInt998 == 0) {
				int k2 = 0xffff00;
				if (anIntArray945[j] < 6)
					k2 = anIntArray842[anIntArray945[j]];
				if (anIntArray945[j] == 6)
					k2 = anInt1138 % 20 >= 10 ? 0xffff00 : 0xff0000;
				if (anIntArray945[j] == 7)
					k2 = anInt1138 % 20 >= 10 ? 65535 : 255;
				if (anIntArray945[j] == 8)
					k2 = anInt1138 % 20 >= 10 ? 0x80ff80 : 45056;
				if (anIntArray945[j] == 9) {
					int l2 = 150 - anIntArray947[j];
					if (l2 < 50)
						k2 = 0xff0000 + 1280 * l2;
					else if (l2 < 100)
						k2 = 0xffff00 - 0x50000 * (l2 - 50);
					else if (l2 < 150)
						k2 = 65280 + 5 * (l2 - 100);
				}
				if (anIntArray945[j] == 10) {
					int i3 = 150 - anIntArray947[j];
					if (i3 < 50)
						k2 = 0xff0000 + 5 * i3;
					else if (i3 < 100)
						k2 = 0xff00ff - 0x50000 * (i3 - 50);
					else if (i3 < 150)
						k2 = (255 + 0x50000 * (i3 - 100)) - 5 * (i3 - 100);
				}
				if (anIntArray945[j] == 11) {
					int j3 = 150 - anIntArray947[j];
					if (j3 < 50)
						k2 = 0xffffff - 0x50005 * j3;
					else if (j3 < 100)
						k2 = 65280 + 0x50005 * (j3 - 50);
					else if (j3 < 150)
						k2 = 0xffffff - 0x50000 * (j3 - 100);
				}
				if (anIntArray946[j] == 0) {
					fontBold.drawStringLeft(s, anInt932,anInt933 + 1, 0);
					fontBold.drawStringLeft(s, anInt932, anInt933, k2);
				}
				if (anIntArray946[j] == 1) {
					fontBold.drawCenteredStringWaveY(s, anInt932, anInt933 + 1, anInt1138, 0);
					fontBold.drawCenteredStringWaveY(s, anInt932, anInt933, anInt1138, k2);
				}
				if (anIntArray946[j] == 2) {
					fontBold.drawCeneteredStringWaveXY(s, anInt932, anInt933 + 1, anInt1138, 0);
					fontBold.drawCeneteredStringWaveXY(s, anInt932, anInt933, anInt1138, k2);
				}
				if (anIntArray946[j] == 3) {
					fontBold.drawCenteredStringWaveXYMove(s, anInt932, anInt933 + 1, anInt1138, 150 - anIntArray947[j], 0
					);
					fontBold.drawCenteredStringWaveXYMove(s, anInt932, anInt933, anInt1138, 150 - anIntArray947[j], k2
					);
				}
				if (anIntArray946[j] == 4) {
					int k3 = fontBold.getStringWidth(s);
					int i4 = ((150 - anIntArray947[j]) * (k3 + 100)) / 150;
					Rasterizer.setCoordinates(0, anInt932 - 50, 334, anInt932 + 50);
					fontBold.drawString(s, (anInt932 + 50) - i4, anInt933 + 1, 0);
					fontBold.drawString(s, (anInt932 + 50) - i4, anInt933, k2);
					Rasterizer.resetCoordinates();
				}
				if (anIntArray946[j] == 5) {
					int l3 = 150 - anIntArray947[j];
					int j4 = 0;
					if (l3 < 25)
						j4 = l3 - 25;
					else if (l3 > 125)
						j4 = l3 - 125;
					Rasterizer.setCoordinates(anInt933 - fontBold.characterDefaultHeight - 1, 0, anInt933 + 5,
							512);
					fontBold.drawStringLeft(s, anInt932, anInt933 + 1 + j4, 0);
					fontBold.drawStringLeft(s, anInt932, anInt933 + j4, k2);
					Rasterizer.resetCoordinates();
				}
			} else {
				fontBold.drawStringLeft(s, anInt932, anInt933 + 1, 0);
				fontBold.drawStringLeft(s, anInt932, anInt933, 0xffff00);
			}
		}

		if (flag)
			opcode = -1;
	}

	public void method122(int i) {
		while (i >= 0)
			aBoolean1242 = !aBoolean1242;
		if (chatboxProducingGraphicsBuffer != null) {
			return;
		} else {
			method141();
			super.imageProducer = null;
			aClass18_1198 = null;
			aClass18_1199 = null;
			aClass18_1200 = null;
			flameLeftBackground = null;
			flameRightBackground = null;
			aClass18_1203 = null;
			aClass18_1204 = null;
			aClass18_1205 = null;
			aClass18_1206 = null;
			chatboxProducingGraphicsBuffer = new ProducingGraphicsBuffer(479, 96, getParentComponent());
			aClass18_1157 = new ProducingGraphicsBuffer(172, 156, getParentComponent());
			Rasterizer.resetPixels();
			minimapBackgroundImage.drawImage(0, 0);
			aClass18_1156 = new ProducingGraphicsBuffer(190, 261, getParentComponent());
			aClass18_1158 = new ProducingGraphicsBuffer(512, 334, getParentComponent());
			Rasterizer.resetPixels();
			aClass18_1108 = new ProducingGraphicsBuffer(496, 50, getParentComponent());
			aClass18_1109 = new ProducingGraphicsBuffer(269, 37, getParentComponent());
			aClass18_1110 = new ProducingGraphicsBuffer(249, 45, getParentComponent());
			aBoolean1046 = true;
			aClass18_1158.createRasterizer();
			Rasterizer3D.lineOffsets = anIntArray1002;
			return;
		}
	}

	public void method123(int i) {
		Graphics g = getParentComponent().getGraphics();
		g.setColor(Color.black);
		i = 68 / i;
		g.fillRect(0, 0, 765, 503);
		setFrameRate(1);
		if (aBoolean1283) {
			aBoolean1243 = false;
			g.setFont(new Font("Helvetica", 1, 16));
			g.setColor(Color.yellow);
			int j = 35;
			g.drawString("Sorry, an error has occured whilst loading RuneScape", 30, j);
			j += 50;
			g.setColor(Color.white);
			g.drawString("To fix this try the following (in order):", 30, j);
			j += 50;
			g.setColor(Color.white);
			g.setFont(new Font("Helvetica", 1, 12));
			g.drawString("1: Try closing ALL open web-browser windows, and reloading", 30, j);
			j += 30;
			g.drawString("2: Try clearing your web-browsers cache from tools->internet options", 30, j);
			j += 30;
			g.drawString("3: Try using a different game-world", 30, j);
			j += 30;
			g.drawString("4: Try rebooting your computer", 30, j);
			j += 30;
			g.drawString("5: Try selecting a different version of Java from the play-game menu", 30, j);
		}
		if (aBoolean1097) {
			aBoolean1243 = false;
			g.setFont(new Font("Helvetica", 1, 20));
			g.setColor(Color.white);
			g.drawString("Error - unable to load game!", 50, 50);
			g.drawString("To play RuneScape make sure you play from", 50, 100);
			g.drawString("http://www.runescape.com", 50, 150);
		}
		if (aBoolean1016) {
			aBoolean1243 = false;
			g.setColor(Color.yellow);
			int k = 35;
			g.drawString("Error a copy of RuneScape already appears to be loaded", 30, k);
			k += 50;
			g.setColor(Color.white);
			g.drawString("To fix this try the following (in order):", 30, k);
			k += 50;
			g.setColor(Color.white);
			g.setFont(new Font("Helvetica", 1, 12));
			g.drawString("1: Try closing ALL open web-browser windows, and reloading", 30, k);
			k += 30;
			g.drawString("2: Try rebooting your computer, and reloading", 30, k);
			k += 30;
		}
	}

	public void logout() {
		try {
			if (gameConnection != null)
				gameConnection.close();
		} catch (Exception _ex) {
		}
		gameConnection = null;
		loggedIn = false;
		loginScreenState = 0;
		username = "";
		password = "";
		resetModelCaches();
		currentScene.method241();
		for (int plane = 0; plane < 4; plane++)
			currentCollisionMap[plane].reset();

		System.gc();

		stopMidi();
		currentSong = -1;
		nextSong = -1;
		previousSong = 0;
	}

	public void method125(String s, String s1) {
		if (aClass18_1158 != null) {
			aClass18_1158.createRasterizer();
			Rasterizer3D.lineOffsets = anIntArray1002;
			int j = 151;
			if (s != null)
				j -= 7;
			fontNormal.drawStringLeft(s1, 257, j, 0);
			fontNormal.drawStringLeft(s1, 256, j - 1, 0xffffff);
			j += 15;
			if (s != null) {
				fontNormal.drawStringLeft(s, 257, j, 0);
				fontNormal.drawStringLeft(s, 256, j - 1, 0xffffff);
			}
			aClass18_1158.drawGraphics(4, 4, super.gameGraphics);
			return;
		}
		if (super.imageProducer != null) {
			super.imageProducer.createRasterizer();
			Rasterizer3D.lineOffsets = anIntArray1003;
			int k = 251;
			char c = '\u012C';
			byte byte0 = 50;
			Rasterizer.drawFilledRectangle(383 - c / 2, k - 5 - byte0 / 2, c, byte0, 0);
			Rasterizer.drawUnfilledRectangle(383 - c / 2, k - 5 - byte0 / 2, c, byte0, 0xffffff);
			if (s != null)
				k -= 7;
			fontNormal.drawStringLeft(s1, 383, k, 0);
			fontNormal.drawStringLeft(s1, 382, k - 1, 0xffffff);
			k += 15;
			if (s != null) {
				fontNormal.drawStringLeft(s, 383, k, 0);
				fontNormal.drawStringLeft(s, 382, k - 1, 0xffffff);
			}
			super.imageProducer.drawGraphics(0, 0, super.gameGraphics);
		}
	}

	public boolean menuHasAddFriend(int i, byte byte0) {
		if (i < 0)
			return false;
		int j = menuActionTypes[i];
		if (byte0 != 97)
			throw new NullPointerException();
		if (j >= 2000)
			j -= 2000;
		return j == 762;
	}

	public void method127(boolean flag) {
		if (!flag) //never remove
			anInt1056 = incomingRandom.nextInt();
		if (anInt1197 != 2)
			return;
		method137((anInt844 - nextTopLeftTileX << 7) + anInt847, anInt846 * 2, (anInt845 - nextTopRightTileY << 7) + anInt848, -214);
		if (anInt932 > -1 && pulseCycle % 20 < 10)
			aClass50_Sub1_Sub1_Sub1Array954[0].drawImage(anInt933 - 28, anInt932 - 12);
	}

	public void repaintGame() {
		if (aBoolean1016 || aBoolean1283 || aBoolean1097) {
			method123(281);
			return;
		}
		anInt1309++;
		if (!loggedIn)
			drawLoginScreen(false);
		else
			method74(7);
		anInt1094 = 0;
	}

	public void method128(boolean flag) {
		if (flag)
			outBuffer.putByte(23);
		int i = menuClickX;
		int j = menuClickY;
		int k = anInt1307;
		int l = anInt1308;
		int i1 = 0x5d5447;
		Rasterizer.drawFilledRectangle(i, j, k, l, i1);
		Rasterizer.drawFilledRectangle(i + 1, j + 1, k - 2, 16, 0);
		Rasterizer.drawUnfilledRectangle(i + 1, j + 18, k - 2, l - 19, 0);
		fontBold.drawString("Choose Option", i + 3, j + 14, i1);
		int j1 = super.mouseX;
		int k1 = super.mouseY;
		if (anInt1304 == 0) {
			j1 -= 4;
			k1 -= 4;
		}
		if (anInt1304 == 1) {
			j1 -= 553;
			k1 -= 205;
		}
		if (anInt1304 == 2) {
			j1 -= 17;
			k1 -= 357;
		}
		for (int l1 = 0; l1 < menuActionRow; l1++) {
			int i2 = j + 31 + (menuActionRow - 1 - l1) * 15;
			int j2 = 0xffffff;
			if (j1 > i && j1 < i + k && k1 > i2 - 13 && k1 < i2 + 3)
				j2 = 0xffff00;
			fontBold.drawShadowedString(menuActionTexts[l1], i + 3, i2, true, j2);
		}

	}

	public int method129(int i, int j, Widget class13) {
		if (i != 3)
			return anInt1222;
		if (class13.opcodes == null || j >= class13.opcodes.length)
			return -2;
		try {
			int ai[] = class13.opcodes[j];
			int k = 0;
			int l = 0;
			int i1 = 0;
			do {
				int j1 = ai[l++];
				int k1 = 0;
				byte byte0 = 0;
				if (j1 == 0)
					return k;
				if (j1 == 1)
					k1 = anIntArray1029[ai[l++]];
				if (j1 == 2)
					k1 = anIntArray1054[ai[l++]];
				if (j1 == 3)
					k1 = anIntArray843[ai[l++]];
				if (j1 == 4) {
					Widget class13_1 = Widget.forId(ai[l++]);
					int k2 = ai[l++];
					if (k2 >= 0 && k2 < ItemDefinition.count && (!ItemDefinition.lookup(k2).members || memberServer)) {
						for (int j3 = 0; j3 < class13_1.items.length; j3++)
							if (class13_1.items[j3] == k2 + 1)
								k1 += class13_1.itemAmounts[j3];

					}
				}
				if (j1 == 5)
					k1 = widgetSettings[ai[l++]];
				if (j1 == 6)
					k1 = SKILL_EXPERIENCE[anIntArray1054[ai[l++]] - 1];
				if (j1 == 7)
					k1 = (widgetSettings[ai[l++]] * 100) / 46875;
				if (j1 == 8)
					k1 = localPlayer.combatLevel;
				if (j1 == 9) {
					for (int l1 = 0; l1 < SkillConstants.SKILL_COUNT; l1++)
						if (SkillConstants.SKILL_TOGGLES[l1])
							k1 += anIntArray1054[l1];

				}
				if (j1 == 10) {
					Widget class13_2 = Widget.forId(ai[l++]);
					int l2 = ai[l++] + 1;
					if (l2 >= 0 && l2 < ItemDefinition.count && (!ItemDefinition.lookup(l2).members || memberServer)) {
						for (int k3 = 0; k3 < class13_2.items.length; k3++) {
							if (class13_2.items[k3] != l2)
								continue;
							k1 = 0x3b9ac9ff;
							break;
						}

					}
				}
				if (j1 == 11)
					k1 = anInt1324;
				if (j1 == 12)
					k1 = anInt1030;
				if (j1 == 13) {
					int i2 = widgetSettings[ai[l++]];
					int i3 = ai[l++];
					k1 = (i2 & 1 << i3) == 0 ? 0 : 1;
				}
				if (j1 == 14) {
					int j2 = ai[l++];
					Varbit class49 = Varbit.cache[j2];
					int l3 = class49.configId;
					int i4 = class49.leastSignificantBit;
					int j4 = class49.mostSignificantBit;
					int k4 = BITFIELD_MAX_VALUE[j4 - i4];
					k1 = widgetSettings[l3] >> i4 & k4;
				}
				if (j1 == 15)
					byte0 = 1;
				if (j1 == 16)
					byte0 = 2;
				if (j1 == 17)
					byte0 = 3;
				if (j1 == 18)
					k1 = (localPlayer.worldX >> 7) + nextTopLeftTileX;
				if (j1 == 19)
					k1 = (localPlayer.worldY >> 7) + nextTopRightTileY;
				if (j1 == 20)
					k1 = ai[l++];
				if (byte0 == 0) {
					if (i1 == 0)
						k += k1;
					if (i1 == 1)
						k -= k1;
					if (i1 == 2 && k1 != 0)
						k /= k1;
					if (i1 == 3)
						k *= k1;
					i1 = 0;
				} else {
					i1 = byte0;
				}
			} while (true);
		} catch (Exception _ex) {
			return -1;
		}
	}

	public void drawOnMinimap(ImageRGB sprite, int x, int y) {
		if (sprite == null)
			return;
		int k = cameraHorizontal + anInt916 & 0x7ff;
		int l = x * x + y * y;
		if (l > 6400)
			return;
		int i1 = Model.SINE[k];
		int j1 = Model.COSINE[k];
		i1 = (i1 * 256) / (anInt1233 + 256);
		j1 = (j1 * 256) / (anInt1233 + 256);
		int k1 = y * i1 + x * j1 >> 16;
		int l1 = y * j1 - x * i1 >> 16;
		if (l > 2500) {
			sprite.method467(minimapBackgroundImage, 83 - l1 - sprite.maxHeight
					/ 2 - 4, -49993, ((94 + k1) - sprite.maxWidth / 2) + 4);
			return;
		} else {
			sprite.drawImage(83 - l1 - sprite.maxHeight / 2 - 4,
					((94 + k1) - sprite.maxWidth / 2) + 4);
			return;
		}
	}

	public void drawLoginScreen(boolean flag) {
		resetTitleScreen();
		aClass18_1200.createRasterizer();
		titleboxImage.drawImage(0, 0);
		char c = '\u0168';
		char c1 = '\310';
		if (loginScreenState == 0) {
			int j = c1 / 2 + 80;
			fontSmall.drawStringCenter(onDemandRequester.message, c / 2, j, 0x75a9a9, true);
			j = c1 / 2 - 20;
			fontBold.drawStringCenter("Welcome to RuneScape", c / 2, j, 0xffff00, true);
			j += 30;
			int i1 = c / 2 - 80;
			int l1 = c1 / 2 + 20;
			titleboxButtonImage.drawImage(i1 - 73, l1 - 20);
			fontBold.drawStringCenter("New User", i1, l1 + 5, 0xffffff, true);
			i1 = c / 2 + 80;
			titleboxButtonImage.drawImage(i1 - 73, l1 - 20);
			fontBold.drawStringCenter("Existing User", i1, l1 + 5, 0xffffff, true);
		}
		if (loginScreenState == 2) {
			int k = c1 / 2 - 40;
			if (statusLineOne.length() > 0) {
				fontBold.drawStringCenter(statusLineOne, c / 2, k - 15, 0xffff00, true);
				fontBold.drawStringCenter(statusLineTwo, c / 2, k, 0xffff00, true);
				k += 30;
			} else {
				fontBold.drawStringCenter(statusLineTwo, c / 2, k - 7, 0xffff00, true);
				k += 30;
			}
			fontBold.drawShadowedString("Username: " + username
					+ ((anInt977 == 0) & (pulseCycle % 40 < 20) ? "@yel@|" : ""), c / 2 - 90, k, true, 0xffffff);
			k += 15;
			fontBold.drawShadowedString("Password: "
					+ TextUtils.censorPassword(password) + ((anInt977 == 1) & (pulseCycle % 40 < 20) ? "@yel@|" : ""), c / 2 - 88, k, true, 0xffffff
			);
			k += 15;
			if (!flag) {
				int j1 = c / 2 - 80;
				int i2 = c1 / 2 + 50;
				titleboxButtonImage.drawImage(j1 - 73, i2 - 20);
				fontBold.drawStringCenter("Login", j1, i2 + 5, 0xffffff, true);
				j1 = c / 2 + 80;
				titleboxButtonImage.drawImage(j1 - 73, i2 - 20);
				fontBold.drawStringCenter("Cancel", j1, i2 + 5, 0xffffff, true);
			}
		}
		if (loginScreenState == 3) {
			fontBold.drawStringCenter("Create a free account", c / 2, c1 / 2 - 60, 0xffff00, true
			);
			int l = c1 / 2 - 35;
			fontBold.drawStringCenter("To create a new account you need to", c / 2, l, 0xffffff, true
			);
			l += 15;
			fontBold.drawStringCenter("go back to the main RuneScape webpage", c / 2, l, 0xffffff, true
			);
			l += 15;
			fontBold.drawStringCenter("and choose the 'create account'", c / 2, l, 0xffffff, true
			);
			l += 15;
			fontBold.drawStringCenter("button near the top of that page.", c / 2, l, 0xffffff, true
			);
			l += 15;
			int k1 = c / 2;
			int j2 = c1 / 2 + 50;
			titleboxButtonImage.drawImage(k1 - 73, j2 - 20);
			fontBold.drawStringCenter("Cancel", k1, j2 + 5, 0xffffff, true);
		}
		aClass18_1200.drawGraphics(202, 171, super.gameGraphics);
		if (aBoolean1046) {
			aBoolean1046 = false;
			aClass18_1198.drawGraphics(128, 0, super.gameGraphics);
			aClass18_1199.drawGraphics(202, 371, super.gameGraphics);
			aClass18_1203.drawGraphics(0, 265, super.gameGraphics);
			aClass18_1204.drawGraphics(562, 265, super.gameGraphics);
			aClass18_1205.drawGraphics(128, 171, super.gameGraphics);
			aClass18_1206.drawGraphics(562, 171, super.gameGraphics);
		}
	}

	public void method132(Buffer class50_sub1_sub2, int i, boolean flag) {
		if (flag)
			anInt1140 = 287;
		while (class50_sub1_sub2.bitPosition + 21 < i * 8) {
			int j = class50_sub1_sub2.getBits(14);
			if (j == 16383)
				break;
			if (npcs[j] == null)
				npcs[j] = new Npc();
			Npc class50_sub1_sub4_sub3_sub1 = npcs[j];
			anIntArray1134[anInt1133++] = j;
			class50_sub1_sub4_sub3_sub1.pulseCycle = pulseCycle;
			int k = class50_sub1_sub2.getBits(1);
			if (k == 1)
				updatedPlayers[updatedPlayerCount++] = j;
			int l = class50_sub1_sub2.getBits(5);
			if (l > 15)
				l -= 32;
			int i1 = class50_sub1_sub2.getBits(5);
			if (i1 > 15)
				i1 -= 32;
			int j1 = class50_sub1_sub2.getBits(1);
			class50_sub1_sub4_sub3_sub1.npcDefinition = ActorDefinition.getDefinition(class50_sub1_sub2.getBits(13));
			class50_sub1_sub4_sub3_sub1.boundaryDimension = class50_sub1_sub4_sub3_sub1.npcDefinition.boundaryDimension;
			class50_sub1_sub4_sub3_sub1.anInt1600 = class50_sub1_sub4_sub3_sub1.npcDefinition.degreesToTurn;
			class50_sub1_sub4_sub3_sub1.walkAnimationId = class50_sub1_sub4_sub3_sub1.npcDefinition.walkAnimationId;
			class50_sub1_sub4_sub3_sub1.turnAroundAnimationId = class50_sub1_sub4_sub3_sub1.npcDefinition.turnAroundAnimationId;
			class50_sub1_sub4_sub3_sub1.turnRightAnimationId = class50_sub1_sub4_sub3_sub1.npcDefinition.turnRightAnimationId;
			class50_sub1_sub4_sub3_sub1.turnLeftAnimationId = class50_sub1_sub4_sub3_sub1.npcDefinition.turnLeftAnimationId;
			class50_sub1_sub4_sub3_sub1.idleAnimation = class50_sub1_sub4_sub3_sub1.npcDefinition.standAnimationId;
			class50_sub1_sub4_sub3_sub1.setPosition(localPlayer.pathX[0] + i1, localPlayer.pathY[0] + l,
					j1 == 1);
		}
		class50_sub1_sub2.finishBitAccess();
	}

	public void playSong(int id) {
		if (currentSong != id) {
			nextSong = id;
			songChanging = true;
			onDemandRequester.request(2, nextSong);
			currentSong = id;
		}
	}

	public void stopMidi() {
		SignLink.music.stop();
		SignLink.fadeMidi = 0;
		SignLink.midi = "stop";
	}

	private void adjustMidiVolume(boolean updateMidi, int volume) {
		SignLink.setVolume(volume);
		if (updateMidi) {
			SignLink.midi = "voladjust";
		}
	}

	public void playSound(int id, int type, int delay, int volume) {
		sound[currentSound] = id;
		soundType[currentSound] = type;
		soundDelay[currentSound] = delay + SoundTrack.trackDelays[id];
		soundVolume[currentSound] = volume;
		currentSound++;
	}

	public void parsePlacementPacket(Buffer buf, int opcode) {
		if (opcode == 203) {
			int k = buf.getUnsignedLEShort();
			int j3 = buf.getUnsignedByte();
			int i6 = j3 >> 2;
			int rotation = j3 & 3;
			int k11 = anIntArray1032[i6];
			byte byte0 = buf.getSignedByteNegated();
			int offset = buf.getByteAdded();
			int x = placementX + (offset >> 4 & 7);
			int y = placementY + (offset & 7);
			byte byte1 = buf.getSignedByteAdded();
			int l19 = buf.method550();
			int id = buf.method549();
			byte byte2 = buf.getSignedByte();
			byte byte3 = buf.getSignedByteAdded();
			int l21 = buf.getUnsignedLEShort();
			Player player;
			if (id == thisPlayerServerId)
				player = localPlayer;
			else
				player = players[id];
			if (player != null) {
				GameObjectDefinition class47 = GameObjectDefinition.getDefinition(k);
				int i22 = anIntArrayArrayArray891[plane][x][y];
				int j22 = anIntArrayArrayArray891[plane][x + 1][y];
				int k22 = anIntArrayArrayArray891[plane][x + 1][y + 1];
				int l22 = anIntArrayArrayArray891[plane][x][y + 1];
				Model class50_sub1_sub4_sub4 = class47.getGameObjectModel(i6, rotation, i22, j22, k22, l22, -1);
				if (class50_sub1_sub4_sub4 != null) {
					method145(true, plane, x, 0, l19 + 1, 0, -1, l21 + 1, k11, y);
					player.objectAppearanceStartTick = l21 + pulseCycle;
					player.objectAppearanceEndTick = l19 + pulseCycle;
					player.playerModel = class50_sub1_sub4_sub4;
					int i23 = class47.sizeX;
					int j23 = class47.sizeY;
					if (rotation == 1 || rotation == 3) {
						i23 = class47.sizeY;
						j23 = class47.sizeX;
					}
					player.anInt1743 = x * 128 + i23 * 64;
					player.anInt1745 = y * 128 + j23 * 64;
					player.anInt1744 = method110(player.anInt1745,
							player.anInt1743, (byte) 9, plane);
					if (byte1 > byte0) {
						byte byte4 = byte1;
						byte1 = byte0;
						byte0 = byte4;
					}
					if (byte3 > byte2) {
						byte byte5 = byte3;
						byte3 = byte2;
						byte2 = byte5;
					}
					player.anInt1768 = x + byte1;
					player.anInt1770 = x + byte0;
					player.anInt1769 = y + byte3;
					player.anInt1771 = y + byte2;
				}
			}
		}
		if (opcode == 106) { // add ground item dropped by player
			int offset = buf.getByteAdded();
			int x = placementX + (offset >> 4 & 7);
			int y = placementY + (offset & 7);
			int amount = buf.getLittleShortA();
			int id = buf.method550();
			int playerId = buf.method550();
			if (x >= 0 && y >= 0 && x < 104 && y < 104 && playerId != thisPlayerServerId) {
				Item item = new Item();
				item.itemId = id;
				item.itemCount = amount;
				if (groundItems[plane][x][y] == null)
					groundItems[plane][x][y] = new LinkedList();
				groundItems[plane][x][y].insertBack(item);
				processGroundItems(x, y);
			}
			return;
		}
		if (opcode == 142) {
			int i1 = buf.getUnsignedLEShort();
			int l3 = buf.getByteAdded();
			int k6 = l3 >> 2;
			int j9 = l3 & 3;
			int i12 = anIntArray1032[k6];
			int j14 = buf.getUnsignedByte();
			int x = placementX + (j14 >> 4 & 7);
			int y = placementY + (j14 & 7);
			if (x >= 0 && y >= 0 && x < 103 && y < 103) {
				int l18 = anIntArrayArrayArray891[plane][x][y];
				int j19 = anIntArrayArrayArray891[plane][x + 1][y];
				int i20 = anIntArrayArrayArray891[plane][x + 1][y + 1];
				int l20 = anIntArrayArrayArray891[plane][x][y + 1];
				if (i12 == 0) {
					Wall wall = currentScene.method263(plane, 17734, x, y);
					if (wall != null) {
						int k21 = wall.hash >> 14 & 0x7fff;
						if (k6 == 2) {
							wall.aRenderable769 = new GameObject(k21, 4 + j9, 2, j19, i20, l18, l20, i1,
									false);
							wall.aRenderable770 = new GameObject(k21, j9 + 1 & 3, 2, j19, i20, l18, l20, i1,
									false);
						} else {
							wall.aRenderable769 = new GameObject(k21, j9, k6, j19, i20, l18, l20, i1,
									false);
						}
					}
				}
				if (i12 == 1) {
					WallDecoration wallDecoration = currentScene.method264(plane, y, x, false);
					if (wallDecoration != null)
						wallDecoration.renderable = new GameObject(wallDecoration.hash >> 14 & 0x7fff, 0, 4, j19, i20, l18, l20, i1,
								false);
				}
				if (i12 == 2) {
					SceneSpawnRequest sceneSpawnRequest = currentScene.method265(x, (byte) 32, y, plane);
					if (k6 == 11)
						k6 = 10;
					if (sceneSpawnRequest != null)
						sceneSpawnRequest.aRenderable601 = new GameObject(sceneSpawnRequest.anInt125 >> 14 & 0x7fff, j9, k6, j19, i20, l18, l20, i1,
								false);
				}
				if (i12 == 3) {
					FloorDecoration floorDecoration = currentScene.method266(plane, y, 0, x);
					if (floorDecoration != null)
						floorDecoration.renderable = new GameObject(floorDecoration.hash >> 14 & 0x7fff, j9, 22, j19, i20, l18, l20, i1,
								false);
				}
			}
			return;
		}
		if (opcode == 107) { // add ground item (dropped by npc or "auto spawn")
			int id = buf.getUnsignedLEShort();
			int offset = buf.getByteNegated();
			int x = placementX + (offset >> 4 & 7);
			int y = placementY + (offset & 7);
			int amount = buf.method550();
			if (x >= 0 && y >= 0 && x < 104 && y < 104) {
				Item item = new Item();
				item.itemId = id;
				item.itemCount = amount;
				if (groundItems[plane][x][y] == null)
					groundItems[plane][x][y] = new LinkedList();
				groundItems[plane][x][y].insertBack(item);
				processGroundItems(x, y);
			}
			return;
		}
		if (opcode == 121) { // update amount of ground item
			int offset = buf.getUnsignedByte();
			int x = placementX + (offset >> 4 & 7);
			int y = placementY + (offset & 7);
			int id = buf.getUnsignedLEShort();
			int amount = buf.getUnsignedLEShort();
			int newAmount = buf.getUnsignedLEShort();
			if (x >= 0 && y >= 0 && x < 104 && y < 104) {
				LinkedList list = groundItems[plane][x][y];
				if (list != null) {
					for (Item item = (Item) list.first(); item != null; item = (Item) list.next()) {
						if (item.itemId != (id & 0x7fff) || item.itemCount != amount)
							continue;
						item.itemCount = newAmount;
						break;
					}

					processGroundItems(x, y);
				}
			}
			return;
		}
		if (opcode == 181) {
			int offset = buf.getUnsignedByte();
			int x = placementX + (offset >> 4 & 7);
			int y = placementY + (offset & 7);
			int i10 = x + buf.getSignedByte();
			int l12 = y + buf.getSignedByte();
			int l14 = buf.getSignedShort();
			int k16 = buf.getUnsignedLEShort();
			int i18 = buf.getUnsignedByte() * 4;
			int i19 = buf.getUnsignedByte() * 4;
			int k19 = buf.getUnsignedLEShort();
			int j20 = buf.getUnsignedLEShort();
			int i21 = buf.getUnsignedByte();
			int j21 = buf.getUnsignedByte();
			if (x >= 0 && y >= 0 && x < 104 && y < 104 && i10 >= 0 && l12 >= 0 && i10 < 104 && l12 < 104
					&& k16 != 65535) {
				x = x * 128 + 64;
				y = y * 128 + 64;
				i10 = i10 * 128 + 64;
				l12 = l12 * 128 + 64;
				Projectile class50_sub1_sub4_sub2 = new Projectile(plane, i19, j21, y,
						k16, j20 + pulseCycle, i21, l14, method110(y, x, (byte) 9, plane) - i18, x, k19 + pulseCycle);
				class50_sub1_sub4_sub2.trackTarget(i10, l12, method110(l12, i10, (byte) 9, plane) - i19, k19
						+ pulseCycle);
				aClass6_1282.insertBack(class50_sub1_sub4_sub2);
			}
			return;
		}
		if (opcode == 41) {
			int offset = buf.getUnsignedByte();
			int x = placementX + (offset >> 4 & 7);
			int y = placementY + (offset & 7);
			int soundId = buf.getUnsignedLEShort();
			int i13 = buf.getUnsignedByte();
			int i15 = i13 >> 4 & 0xf;
			int type = i13 & 7;
			if (localPlayer.pathX[0] >= x - i15
					&& localPlayer.pathX[0] <= x + i15
					&& localPlayer.pathY[0] >= y - i15
					&& localPlayer.pathY[0] <= y + i15 && aBoolean1301 && !lowMemory
					&& currentSound < 50) {
				sound[currentSound] = soundId;
				soundType[currentSound] = type;
				soundDelay[currentSound] = SoundTrack.trackDelays[soundId];
				currentSound++;
			}
		}
		if (opcode == 59) {
			int j2 = buf.getUnsignedByte();
			int i5 = placementX + (j2 >> 4 & 7);
			int l7 = placementY + (j2 & 7);
			int k10 = buf.getUnsignedLEShort();
			int j13 = buf.getUnsignedByte();
			int j15 = buf.getUnsignedLEShort();
			if (i5 >= 0 && l7 >= 0 && i5 < 104 && l7 < 104) {
				i5 = i5 * 128 + 64;
				l7 = l7 * 128 + 64;
				GameAnimableObject gameAnimableObject = new GameAnimableObject(plane, pulseCycle, j15, k10, method110(l7, i5, (byte) 9, plane) - j13, l7, i5);
				aClass6_1210.insertBack(gameAnimableObject);
			}
			return;
		}
		if (opcode == 152) {
			int k2 = buf.getByteNegated();
			int j5 = k2 >> 2;
			int i8 = k2 & 3;
			int l10 = anIntArray1032[j5];
			int k13 = buf.getLittleShortA();
			int k15 = buf.getByteAdded();
			int i17 = placementX + (k15 >> 4 & 7);
			int j18 = placementY + (k15 & 7);
			if (i17 >= 0 && j18 >= 0 && i17 < 104 && j18 < 104)
				method145(true, plane, i17, i8, -1, j5, k13, 0, l10, j18);
			return;
		}
		if (opcode == 208) { // remove ground item
			int id = buf.method550();
			int offset = buf.getByteAdded();
			int x = placementX + (offset >> 4 & 7);
			int y = placementY + (offset & 7);
			if (x >= 0 && y >= 0 && x < 104 && y < 104) {
				LinkedList list = groundItems[plane][x][y];
				if (list != null) {
					for (Item item = (Item) list.first(); item != null; item = (Item) list.next()) {
						if (item.itemId != (id & 0x7fff))
							continue;
						item.remove();
						break;
					}

					if (list.first() == null)
						groundItems[plane][x][y] = null;
					processGroundItems(x, y);
				}
			}
			return;
		}
		if (opcode == 88) {
			int i3 = buf.getByteSubtracted();
			int l5 = placementX + (i3 >> 4 & 7);
			int k8 = placementY + (i3 & 7);
			int j11 = buf.getByteSubtracted();
			int l13 = j11 >> 2;
			int l15 = j11 & 3;
			int j17 = anIntArray1032[l13];
			if (l5 >= 0 && k8 >= 0 && l5 < 104 && k8 < 104)
				method145(true, plane, l5, l15, -1, l13, -1, 0, j17, k8);
		}
	}

	public void method134(byte byte0) {
		aClass18_1156.createRasterizer();
		Rasterizer3D.lineOffsets = anIntArray1001;
		inventoryBackgroundImage.drawImage(0, 0);
		if (anInt1089 != -1)
			method142(0, 0, Widget.forId(anInt1089), 0, 8);
		else if (anIntArray1081[anInt1285] != -1)
			method142(0, 0, Widget.forId(anIntArray1081[anInt1285]), 0, 8);
		if (menuOpen && anInt1304 == 1)
			method128(false);
		aClass18_1156.drawGraphics(553, 205, super.gameGraphics);
		aClass18_1158.createRasterizer();
		Rasterizer3D.lineOffsets = anIntArray1002;
		if (byte0 == 7)
			;
	}



	public void method136(Actor class50_sub1_sub4_sub3, boolean flag, int i) {
		method137(class50_sub1_sub4_sub3.worldX, i, class50_sub1_sub4_sub3.worldY, -214);
		if (!flag)
			;
	}

	public void method137(int i, int j, int k, int l) {
		if (i < 128 || k < 128 || i > 13056 || k > 13056) {
			anInt932 = -1;
			anInt933 = -1;
			return;
		}
		int i1 = method110(k, i, (byte) 9, plane) - j;
		i -= cameraX;
		i1 -= cameraZ;
		k -= cameraY;
		int j1 = Model.SINE[anInt1219];
		int k1 = Model.COSINE[anInt1219];
		int l1 = Model.SINE[anInt1220];
		int i2 = Model.COSINE[anInt1220];
		int j2 = k * l1 + i * i2 >> 16;
		k = k * i2 - i * l1 >> 16;
		i = j2;
		j2 = i1 * k1 - k * j1 >> 16;
		k = i1 * j1 + k * k1 >> 16;
		while (l >= 0)
			opcode = -1;
		i1 = j2;
		if (k >= 50) {
			anInt932 = Rasterizer3D.centerX + (i << 9) / k;
			anInt933 = Rasterizer3D.centerY + (i1 << 9) / k;
			return;
		} else {
			anInt932 = -1;
			anInt933 = -1;
			return;
		}
	}

	public void method138() {
		System.out.println("============");
		System.out.println("flame-cycle:" + anInt1101);
		if (onDemandRequester != null)
			System.out.println("Od-cycle:" + onDemandRequester.cycle);
		System.out.println("loop-cycle:" + pulseCycle);
		System.out.println("draw-cycle:" + anInt1309);
		System.out.println("ptype:" + opcode);
		System.out.println("psize:" + packetSize);
		if (gameConnection != null)
			gameConnection.printDebug();
		super.dumpRequested = true;
	}

	public Component getParentComponent() {
		if (SignLink.applet != null)
			return SignLink.applet;
		if (super.gameFrame != null)
			return super.gameFrame;
		else
			return this;
	}

	public void drawLoadingText(int i, String s) {
		anInt1322 = i;
		aString1027 = s;
		resetTitleScreen();
		if (titleArchive == null) {
			super.drawLoadingText(i, s);
			return;
		}
		aClass18_1200.createRasterizer();
		char c = '\u0168';
		char c1 = '\310';
		byte byte0 = 20;
		fontBold.drawStringLeft("RuneScape is loading - please wait...", c / 2, c1 / 2 - 26 - byte0, 0xffffff
		);
		int j = c1 / 2 - 18 - byte0;
		Rasterizer.drawUnfilledRectangle(c / 2 - 152, j, 304, 34, 0x8c1111);
		Rasterizer.drawUnfilledRectangle(c / 2 - 151, j + 1, 302, 32, 0);
		Rasterizer.drawFilledRectangle(c / 2 - 150, j + 2, i * 3, 30, 0x8c1111);
		Rasterizer.drawFilledRectangle((c / 2 - 150) + i * 3, j + 2, 300 - i * 3, 30, 0);
		fontBold.drawStringLeft(s, c / 2, (c1 / 2 + 5) - byte0, 0xffffff);
		aClass18_1200.drawGraphics(202, 171, super.gameGraphics);
		if (aBoolean1046) {
			aBoolean1046 = false;
			if (!aBoolean1243) {
				flameLeftBackground.drawGraphics(0, 0, super.gameGraphics);
				flameRightBackground.drawGraphics(637, 0, super.gameGraphics);
			}
			aClass18_1198.drawGraphics(128, 0, super.gameGraphics);
			aClass18_1199.drawGraphics(202, 371, super.gameGraphics);
			aClass18_1203.drawGraphics(0, 265, super.gameGraphics);
			aClass18_1204.drawGraphics(562, 265, super.gameGraphics);
			aClass18_1205.drawGraphics(128, 171, super.gameGraphics);
			aClass18_1206.drawGraphics(562, 171, super.gameGraphics);
		}
	}

	public void prepareTitleBackground() {
		byte abyte0[] = titleArchive.getFile("title.dat");
		ImageRGB class50_sub1_sub1_sub1 = new ImageRGB(abyte0, this);
		flameLeftBackground.createRasterizer();
		class50_sub1_sub1_sub1.drawInverse(0, 0);
		flameRightBackground.createRasterizer();
		class50_sub1_sub1_sub1.drawInverse(-637, 0);
		aClass18_1198.createRasterizer();
		class50_sub1_sub1_sub1.drawInverse(-128, 0);
		aClass18_1199.createRasterizer();
		class50_sub1_sub1_sub1.drawInverse(-202, -371);
		aClass18_1200.createRasterizer();
		class50_sub1_sub1_sub1.drawInverse(-202, -171);
		aClass18_1203.createRasterizer();
		class50_sub1_sub1_sub1.drawInverse(0, -265);
		aClass18_1204.createRasterizer();
		class50_sub1_sub1_sub1.drawInverse(-562, -265);
		aClass18_1205.createRasterizer();
		class50_sub1_sub1_sub1.drawInverse(-128, -171);
		aClass18_1206.createRasterizer();
		class50_sub1_sub1_sub1.drawInverse(-562, -171);
		int ai[] = new int[class50_sub1_sub1_sub1.width];
		for (int i = 0; i < class50_sub1_sub1_sub1.height; i++) {
			for (int j = 0; j < class50_sub1_sub1_sub1.width; j++)
				ai[j] = class50_sub1_sub1_sub1.pixels[(class50_sub1_sub1_sub1.width - j - 1)
						+ class50_sub1_sub1_sub1.width * i];

			for (int l = 0; l < class50_sub1_sub1_sub1.width; l++)
				class50_sub1_sub1_sub1.pixels[l + class50_sub1_sub1_sub1.width * i] = ai[l];

		}

		flameLeftBackground.createRasterizer();
		class50_sub1_sub1_sub1.drawInverse(382, 0);
		flameRightBackground.createRasterizer();
		class50_sub1_sub1_sub1.drawInverse(-255, 0);
		aClass18_1198.createRasterizer();
		class50_sub1_sub1_sub1.drawInverse(254, 0);
		aClass18_1199.createRasterizer();
		class50_sub1_sub1_sub1.drawInverse(180, -371);
		aClass18_1200.createRasterizer();
		class50_sub1_sub1_sub1.drawInverse(180, -171);
		aClass18_1203.createRasterizer();
		class50_sub1_sub1_sub1.drawInverse(382, -265);
		aClass18_1204.createRasterizer();
		class50_sub1_sub1_sub1.drawInverse(-180, -265);
		aClass18_1205.createRasterizer();
		class50_sub1_sub1_sub1.drawInverse(254, -171);
		aClass18_1206.createRasterizer();
		class50_sub1_sub1_sub1.drawInverse(-180, -171);
		class50_sub1_sub1_sub1 = new ImageRGB(titleArchive, "logo", 0);
		aClass18_1198.createRasterizer();
		class50_sub1_sub1_sub1.drawImage(18, 382 - class50_sub1_sub1_sub1.width / 2 - 128);
		class50_sub1_sub1_sub1 = null;
		abyte0 = null;
		ai = null;
		System.gc();
	}

	public void method140(byte byte0, SpawnObjectNode spawnObjectNode) {
		int i = 0;
		int j = -1;
		int k = 0;
		int l = 0;
		if (byte0 != -61)
			outBuffer.putByte(175);
		if (spawnObjectNode.anInt1392 == 0)
			i = currentScene.method267(spawnObjectNode.anInt1391, spawnObjectNode.anInt1393, spawnObjectNode.anInt1394);
		if (spawnObjectNode.anInt1392 == 1)
			i = currentScene.method268(spawnObjectNode.anInt1393, (byte) 4, spawnObjectNode.anInt1391,
					spawnObjectNode.anInt1394);
		if (spawnObjectNode.anInt1392 == 2)
			i = currentScene.method269(spawnObjectNode.anInt1391, spawnObjectNode.anInt1393, spawnObjectNode.anInt1394);
		if (spawnObjectNode.anInt1392 == 3)
			i = currentScene.getFloorDecorationHash(spawnObjectNode.anInt1391, spawnObjectNode.anInt1393, spawnObjectNode.anInt1394);
		if (i != 0) {
			int i1 = currentScene.method271(spawnObjectNode.anInt1391, spawnObjectNode.anInt1393, spawnObjectNode.anInt1394, i);
			j = i >> 14 & 0x7fff;
			k = i1 & 0x1f;
			l = i1 >> 6;
		}
		spawnObjectNode.anInt1387 = j;
		spawnObjectNode.anInt1389 = k;
		spawnObjectNode.anInt1388 = l;
	}

	public void method141() {
		aBoolean1243 = false;
		while (aBoolean1320) {
			aBoolean1243 = false;
			try {
				Thread.sleep(50L);
			} catch (Exception _ex) {
			}
		}
		titleboxImage = null;
		titleboxButtonImage = null;
		titleFlameEmblem = null;
		anIntArray1310 = null;
		anIntArray1311 = null;
		anIntArray1312 = null;
		anIntArray1313 = null;
		anIntArray1176 = null;
		anIntArray1177 = null;
		anIntArray1084 = null;
		anIntArray1085 = null;
		anImageRGB1226 = null;
		anImageRGB1227 = null;
	}

	public void method142(int i, int j, Widget class13, int k, int l) {
		if (class13.type != 0 || class13.children == null)
			return;
		if (class13.hiddenUntilHovered && anInt1302 != class13.id && anInt1280 != class13.id
				&& anInt1106 != class13.id)
			return;
		int i1 = Rasterizer.topX;
		int j1 = Rasterizer.topY;
		int k1 = Rasterizer.bottomX;
		int l1 = Rasterizer.bottomY;
		Rasterizer.setCoordinates(i, j, i + class13.height, j + class13.width);
		int i2 = class13.children.length;
		if (l != 8)
			opcode = -1;
		for (int j2 = 0; j2 < i2; j2++) {
			int k2 = class13.childrenX[j2] + j;
			int l2 = (class13.childrenY[j2] + i) - k;
			Widget child = Widget.forId(class13.children[j2]);
			k2 += child.anInt228;
			l2 += child.anInt259;
			if (child.contentType > 0)
				method103((byte) 2, child);
			if (child.type == 0) {
				if (child.anInt231 > child.scrollLimit - child.height)
					child.anInt231 = child.scrollLimit - child.height;
				if (child.anInt231 < 0)
					child.anInt231 = 0;
				method142(l2, k2, child, child.anInt231, 8);
				if (child.scrollLimit > child.height)
					method56(true, child.anInt231, k2 + child.width, child.height, child.scrollLimit,
							l2);
			} else if (child.type != 1)
				if (child.type == 2) {
					int i3 = 0;
					for (int i4 = 0; i4 < child.height; i4++) {
						for (int j5 = 0; j5 < child.width; j5++) {
							int i6 = k2 + j5 * (32 + child.itemSpritePadsX);
							int l6 = l2 + i4 * (32 + child.itemSpritePadsY);
							if (i3 < 20) {
								i6 += child.imageX[i3];
								l6 += child.imageY[i3];
							}
							if (child.items[i3] > 0) {
								int i7 = 0;
								int j8 = 0;
								int l10 = child.items[i3] - 1;
								if (i6 > Rasterizer.topX - 32 && i6 < Rasterizer.bottomX
										&& l6 > Rasterizer.topY - 32 && l6 < Rasterizer.bottomY
										|| activeInterfaceType != 0 && selectedInventorySlot == i3) {
									int k11 = 0;
									if (itemSelected == 1 && anInt1147 == i3 && anInt1148 == child.id)
										k11 = 0xffffff;
									ImageRGB class50_sub1_sub1_sub1_2 = ItemDefinition.sprite(
											l10, child.itemAmounts[i3], k11);
									if (class50_sub1_sub1_sub1_2 != null) {
										if (activeInterfaceType != 0 && selectedInventorySlot == i3 && modifiedWidgetId == child.id) {
											i7 = super.mouseX - anInt1114;
											j8 = super.mouseY - anInt1115;
											if (i7 < 5 && i7 > -5)
												i7 = 0;
											if (j8 < 5 && j8 > -5)
												j8 = 0;
											if (anInt1269 < 5) {
												i7 = 0;
												j8 = 0;
											}
											class50_sub1_sub1_sub1_2.drawImageAlpha(i6 + i7, l6 + j8, 128);
											if (l6 + j8 < Rasterizer.topY && class13.anInt231 > 0) {
												int i12 = (tickDelta * (Rasterizer.topY - l6 - j8)) / 3;
												if (i12 > tickDelta * 10)
													i12 = tickDelta * 10;
												if (i12 > class13.anInt231)
													i12 = class13.anInt231;
												class13.anInt231 -= i12;
												anInt1115 += i12;
											}
											if (l6 + j8 + 32 > Rasterizer.bottomY
													&& class13.anInt231 < class13.scrollLimit - class13.height) {
												int j12 = (tickDelta * ((l6 + j8 + 32) - Rasterizer.bottomY)) / 3;
												if (j12 > tickDelta * 10)
													j12 = tickDelta * 10;
												if (j12 > class13.scrollLimit - class13.height - class13.anInt231)
													j12 = class13.scrollLimit - class13.height - class13.anInt231;
												class13.anInt231 += j12;
												anInt1115 -= j12;
											}
										} else if (atInventoryInterfaceType != 0 && anInt1331 == i3 && anInt1330 == child.id)
											class50_sub1_sub1_sub1_2.drawImageAlpha(i6, l6, 128);
										else
											class50_sub1_sub1_sub1_2.drawImage(l6, i6);
										if (class50_sub1_sub1_sub1_2.maxWidth == 33 || child.itemAmounts[i3] != 1) {
											int k12 = child.itemAmounts[i3];
											fontSmall.drawString(getShortenedAmountText(k12), i6 + 1 + i7, l6 + 10 + j8, 0
											);
											fontSmall.drawString(getShortenedAmountText(k12), i6 + i7, l6 + 9 + j8, 0xffff00
											);
										}
									}
								}
							} else if (child.images != null && i3 < 20) {
								ImageRGB class50_sub1_sub1_sub1_1 = child.images[i3];
								if (class50_sub1_sub1_sub1_1 != null)
									class50_sub1_sub1_sub1_1.drawImage(l6, i6);
							}
							i3++;
						}

					}

				} else if (child.type == 3) {
					boolean flag = false;
					if (anInt1106 == child.id || anInt1280 == child.id
							|| anInt1302 == child.id)
						flag = true;
					int j3;
					if (method95(child, -693)) {
						j3 = child.enabledColor;
						if (flag && child.enabledHoveredColor != 0)
							j3 = child.enabledHoveredColor;
					} else {
						j3 = child.disabledColor;
						if (flag && child.disabledHoveredColor != 0)
							j3 = child.disabledHoveredColor;
					}
					if (child.alpha == 0) {
						if (child.filled)
							Rasterizer.drawFilledRectangle(k2, l2, child.width, child.height, j3);
						else
							Rasterizer.drawUnfilledRectangle(k2, l2, child.width, child.height, j3);
					} else if (child.filled)
						Rasterizer.drawFilledRectangleAlhpa(k2, l2, child.width, child.height, j3,
								256 - (child.alpha & 0xff));
					else
						Rasterizer.drawUnfilledRectangleAlpha(k2, l2, child.width, child.height, j3,
								256 - (child.alpha & 0xff));
				} else if (child.type == 4) {
					TypeFace class50_sub1_sub1_sub2 = child.typeFaces;
					String s = child.disabledText;
					boolean flag1 = false;
					if (anInt1106 == child.id || anInt1280 == child.id
							|| anInt1302 == child.id)
						flag1 = true;
					int j4;
					if (method95(child, -693)) {
						j4 = child.enabledColor;
						if (flag1 && child.enabledHoveredColor != 0)
							j4 = child.enabledHoveredColor;
						if (child.enabledText.length() > 0)
							s = child.enabledText;
					} else {
						j4 = child.disabledColor;
						if (flag1 && child.disabledHoveredColor != 0)
							j4 = child.disabledHoveredColor;
					}
					if (child.actionType == 6 && aBoolean1239) {
						s = "Please wait...";
						j4 = child.disabledColor;
					}
					if (Rasterizer.width == 479) {
						if (j4 == 0xffff00)
							j4 = 255;
						if (j4 == 49152)
							j4 = 0xffffff;
					}
					for (int j7 = l2 + class50_sub1_sub1_sub2.characterDefaultHeight; s.length() > 0; j7 += class50_sub1_sub1_sub2.characterDefaultHeight) {
						if (s.indexOf("%") != -1) {
							do {
								int k8 = s.indexOf("%1");
								if (k8 == -1)
									break;
								s = s.substring(0, k8) + method89(method129(3, 0, child), 8) + s.substring(k8 + 2);
							} while (true);
							do {
								int l8 = s.indexOf("%2");
								if (l8 == -1)
									break;
								s = s.substring(0, l8) + method89(method129(3, 1, child), 8) + s.substring(l8 + 2);
							} while (true);
							do {
								int i9 = s.indexOf("%3");
								if (i9 == -1)
									break;
								s = s.substring(0, i9) + method89(method129(3, 2, child), 8) + s.substring(i9 + 2);
							} while (true);
							do {
								int j9 = s.indexOf("%4");
								if (j9 == -1)
									break;
								s = s.substring(0, j9) + method89(method129(3, 3, child), 8) + s.substring(j9 + 2);
							} while (true);
							do {
								int k9 = s.indexOf("%5");
								if (k9 == -1)
									break;
								s = s.substring(0, k9) + method89(method129(3, 4, child), 8) + s.substring(k9 + 2);
							} while (true);
						}
						int l9 = s.indexOf("\\n");
						String s3;
						if (l9 != -1) {
							s3 = s.substring(0, l9);
							s = s.substring(l9 + 2);
						} else {
							s3 = s;
							s = "";
						}
						if (child.typeFaceCentered)
							class50_sub1_sub1_sub2.drawStringCenter(s3, k2
									+ child.width / 2, j7, j4, child.typeFaceShadowed);
						else
							class50_sub1_sub1_sub2.drawShadowedString(s3, k2, j7, child.typeFaceShadowed, j4);
					}

				} else if (child.type == 5) {
					ImageRGB class50_sub1_sub1_sub1;
					if (method95(child, -693))
						class50_sub1_sub1_sub1 = child.enabledImage;
					else
						class50_sub1_sub1_sub1 = child.disabledImage;
					switch (child.id){
						case 1164:
						case 1167:
						case 1170:
						case 1174:
						case 1540:
						case 1541:
						case 7455:
							class50_sub1_sub1_sub1 =  child.enabledImage;
							break;
						default:
							break;
					}
					if (class50_sub1_sub1_sub1 != null)
						class50_sub1_sub1_sub1.drawImage(l2, k2);
				} else if (child.type == 6) {
					int k3 = Rasterizer3D.centerX;
					int k4 = Rasterizer3D.centerY;
					Rasterizer3D.centerX = k2 + child.width / 2;
					Rasterizer3D.centerY = l2 + child.height / 2;
					int k5 = Rasterizer3D.SINE[child.rotationX] * child.zoom >> 16;
					int j6 = Rasterizer3D.COSINE[child.rotationX] * child.zoom >> 16;
					boolean flag2 = method95(child, -693);
					int k7;
					if (flag2)
						k7 = child.enabledAnimation;
					else
						k7 = child.disabledAnimation;
					Model class50_sub1_sub4_sub4;
					if (k7 == -1) {
						class50_sub1_sub4_sub4 = child.getAnimatedModel(-1, -1, flag2);
					} else {
						AnimationSequence class14 = AnimationSequence.animations[k7];
						class50_sub1_sub4_sub4 = child.getAnimatedModel(class14.frame1Ids[child.anInt235], class14.getPrimaryFrame[child.anInt235],
								flag2);
					}
					if (class50_sub1_sub4_sub4 != null)
						class50_sub1_sub4_sub4.render(0, child.rotationY, 0, child.rotationX, 0, k5, j6);
					Rasterizer3D.centerX = k3;
					Rasterizer3D.centerY = k4;
				} else {
					if (child.type == 7) {
						TypeFace class50_sub1_sub1_sub2_1 = child.typeFaces;
						int l4 = 0;
						for (int l5 = 0; l5 < child.height; l5++) {
							for (int k6 = 0; k6 < child.width; k6++) {
								if (child.items[l4] > 0) {
									ItemDefinition class16 = ItemDefinition.lookup(child.items[l4] - 1);
									String s6 = String.valueOf(class16.name);
									if (class16.stackable || child.itemAmounts[l4] != 1)
										s6 = s6 + " x" + getFullAmountText(child.itemAmounts[l4]);
									int i10 = k2 + k6 * (115 + child.itemSpritePadsX);
									int i11 = l2 + l5 * (12 + child.itemSpritePadsY);
									if (child.typeFaceCentered)
										class50_sub1_sub1_sub2_1.drawStringCenter(s6, i10 + child.width / 2, i11, child.disabledColor, child.typeFaceShadowed
										);
									else
										class50_sub1_sub1_sub2_1.drawShadowedString(s6, i10, i11, child.typeFaceShadowed, child.disabledColor
										);
								}
								l4++;
							}

						}

					}
					if (child.type == 8
							&& (anInt1284 == child.id || anInt1044 == child.id || anInt1129 == child.id)
							&& anInt893 == 100) {
						int l3 = 0;
						int i5 = 0;
						TypeFace class50_sub1_sub1_sub2_2 = fontNormal;
						for (String s1 = child.disabledText; s1.length() > 0;) {
							int l7 = s1.indexOf("\\n");
							String s4;
							if (l7 != -1) {
								s4 = s1.substring(0, l7);
								s1 = s1.substring(l7 + 2);
							} else {
								s4 = s1;
								s1 = "";
							}
							int j10 = class50_sub1_sub1_sub2_2.getStringEffectWidth(s4);
							if (j10 > l3)
								l3 = j10;
							i5 += class50_sub1_sub1_sub2_2.characterDefaultHeight + 1;
						}

						l3 += 6;
						i5 += 7;
						int i8 = (k2 + child.width) - 5 - l3;
						int k10 = l2 + child.height + 5;
						if (i8 < k2 + 5)
							i8 = k2 + 5;
						if (i8 + l3 > j + class13.width)
							i8 = (j + class13.width) - l3;
						if (k10 + i5 > i + class13.height)
							k10 = (i + class13.height) - i5;
						Rasterizer.drawFilledRectangle(i8, k10, l3, i5, 0xffffa0);
						Rasterizer.drawUnfilledRectangle(i8, k10, l3, i5, 0);
						String s2 = child.disabledText;
						for (int j11 = k10 + class50_sub1_sub1_sub2_2.characterDefaultHeight + 2; s2.length() > 0; j11 += class50_sub1_sub1_sub2_2.characterDefaultHeight + 1) {
							int l11 = s2.indexOf("\\n");
							String s5;
							if (l11 != -1) {
								s5 = s2.substring(0, l11);
								s2 = s2.substring(l11 + 2);
							} else {
								s5 = s2;
								s2 = "";
							}
							class50_sub1_sub1_sub2_2.drawShadowedString(s5, i8 + 3, j11, false, 0);
						}

					}
				}
		}

		Rasterizer.setCoordinates(j1, i1, l1, k1);
	}

	public void method143(byte byte0) {
		if (byte0 != -40)
			aBoolean1207 = !aBoolean1207;
		if (lowMemory && loadingStage == 2 && Region.onBuildTimePlane != plane) {
			method125(null, "Loading - please wait.");
			loadingStage = 1;
			aLong1229 = System.currentTimeMillis();
		}
		if (loadingStage == 1) {
			int i = method144(5);
			if (i != 0 && System.currentTimeMillis() - aLong1229 > 0x57e40L) {
				SignLink.reportError(username + " glcfb " + serverSeed + "," + i + "," + lowMemory + ","
						+ stores[0] + "," + onDemandRequester.method333() + "," + plane + ","
						+ chunkX + "," + chunkY);
				aLong1229 = System.currentTimeMillis();
			}
		}
		if (loadingStage == 2 && plane != anInt1276) {
			anInt1276 = plane;
			renderViewport(plane);
		}
	}

	public int method144(int i) {
		for (int j = 0; j < aByteArrayArray838.length; j++) {
			if (aByteArrayArray838[j] == null && anIntArray857[j] != -1)
				return -1;
			if (aByteArrayArray1232[j] == null && anIntArray858[j] != -1)
				return -2;
		}

		boolean flag = true;
		if (i < 5 || i > 5)
			aBoolean953 = !aBoolean953;
		for (int k = 0; k < aByteArrayArray838.length; k++) {
			byte abyte0[] = aByteArrayArray1232[k];
			if (abyte0 != null) {
				int l = (coordinates[k] >> 8) * 64 - nextTopLeftTileX;
				int i1 = (coordinates[k] & 0xff) * 64 - nextTopRightTileY;
				if (aBoolean1163) {
					l = 10;
					i1 = 10;
				}
				flag &= Region.method181(l, i1, abyte0, 24515);
			}
		}

		if (!flag)
			return -3;
		if (aBoolean1209) {
			return -4;
		} else {
			loadingStage = 2;
			Region.onBuildTimePlane = plane;
			method93(175);
			outBuffer.putOpcode(6);
			return 0;
		}
	}

	public void method145(boolean flag, int i, int j, int k, int l, int i1, int j1, int k1, int l1, int i2) {
		SpawnObjectNode spawnObjectNode = null;
		for (SpawnObjectNode spawnObjectNode_1 = (SpawnObjectNode) aClass6_1261.first(); spawnObjectNode_1 != null; spawnObjectNode_1 = (SpawnObjectNode) aClass6_1261
				.next()) {
			if (spawnObjectNode_1.anInt1391 != i || spawnObjectNode_1.anInt1393 != j || spawnObjectNode_1.anInt1394 != i2
					|| spawnObjectNode_1.anInt1392 != l1)
				continue;
			spawnObjectNode = spawnObjectNode_1;
			break;
		}

		if (spawnObjectNode == null) {
			spawnObjectNode = new SpawnObjectNode();
			spawnObjectNode.anInt1391 = i;
			spawnObjectNode.anInt1392 = l1;
			spawnObjectNode.anInt1393 = j;
			spawnObjectNode.anInt1394 = i2;
			method140((byte) -61, spawnObjectNode);
			aClass6_1261.insertBack(spawnObjectNode);
		}
		spawnObjectNode.anInt1384 = j1;
		spawnObjectNode.anInt1386 = i1;
		spawnObjectNode.anInt1385 = k;
		spawnObjectNode.anInt1395 = k1;
		spawnObjectNode.anInt1390 = l;
		loggedIn &= flag;
	}

	public void method146(byte byte0) {
		if (byte0 != 4)
			return;
		if (minimapState != 0)
			return;
		if (super.clickType == 1) {
			int i = super.clickX - 25 - 550;
			int j = super.clickY - 5 - 4;
			if (i >= 0 && j >= 0 && i < 146 && j < 151) {
				i -= 73;
				j -= 75;
				int k = cameraHorizontal + anInt916 & 0x7ff;
				int l = Rasterizer3D.SINE[k];
				int i1 = Rasterizer3D.COSINE[k];
				l = l * (anInt1233 + 256) >> 8;
				i1 = i1 * (anInt1233 + 256) >> 8;
				int j1 = j * l + i * i1 >> 11;
				int k1 = j * i1 - i * l >> 11;
				int l1 = localPlayer.worldX + j1 >> 7;
				int i2 = localPlayer.worldY - k1 >> 7;
				boolean flag = walk(true, false, i2, localPlayer.pathY[0], 0, 0, 1, 0, l1,
						0, 0, localPlayer.pathX[0]);
				if (flag) {
					outBuffer.putByte(i);
					outBuffer.putByte(j);
					outBuffer.putShort(cameraHorizontal);
					outBuffer.putByte(57);
					outBuffer.putByte(anInt916);
					outBuffer.putByte(anInt1233);
					outBuffer.putByte(89);
					outBuffer.putShort(localPlayer.worldX);
					outBuffer.putShort(localPlayer.worldY);
					outBuffer.putByte(anInt1126);
					outBuffer.putByte(63);
				}
			}
		}
	}

	public void method147(int i) {
		if (super.imageProducer != null)
			return;
		method141();
		aClass18_1198 = null;
		aClass18_1199 = null;
		aClass18_1200 = null;
		if (i >= 0)
			anInt1004 = -4;
		flameLeftBackground = null;
		flameRightBackground = null;
		aClass18_1203 = null;
		aClass18_1204 = null;
		aClass18_1205 = null;
		aClass18_1206 = null;
		chatboxProducingGraphicsBuffer = null;
		aClass18_1157 = null;
		aClass18_1156 = null;
		aClass18_1158 = null;
		aClass18_1108 = null;
		aClass18_1109 = null;
		aClass18_1110 = null;
		super.imageProducer = new ProducingGraphicsBuffer(765, 503, getParentComponent());
		aBoolean1046 = true;
	}

	public boolean method148(int i, String s) {
		if (s == null)
			return false;
		for (int j = 0; j < friendsCount; j++)
			if (s.equalsIgnoreCase(friendUsernames[j]))
				return true;

		if (i != 13292)
			aBoolean1014 = !aBoolean1014;
		return s.equalsIgnoreCase(localPlayer.playerName);
	}

	public void method149(int i) {
		while (i >= 0)
			opcode = buffer.getUnsignedByte();
		if (loginScreenState == 0) {
			int j = super.width / 2 - 80;
			int i1 = super.height / 2 + 20;
			i1 += 20;
			if (super.clickType == 1 && super.clickX >= j - 75 && super.clickX <= j + 75 && super.clickY >= i1 - 20
					&& super.clickY <= i1 + 20) {
				loginScreenState = 3;
				anInt977 = 0;
			}
			j = super.width / 2 + 80;
			if (super.clickType == 1 && super.clickX >= j - 75 && super.clickX <= j + 75 && super.clickY >= i1 - 20
					&& super.clickY <= i1 + 20) {
				statusLineOne = "";
				statusLineTwo = "Enter your username & password.";
				loginScreenState = 2;
				anInt977 = 0;
				return;
			}
		} else {
			if (loginScreenState == 2) {
				int k = super.height / 2 - 40;
				k += 30;
				k += 25;
				if (super.clickType == 1 && super.clickY >= k - 15 && super.clickY < k)
					anInt977 = 0;
				k += 15;
				if (super.clickType == 1 && super.clickY >= k - 15 && super.clickY < k)
					anInt977 = 1;
				k += 15;
				int j1 = super.width / 2 - 80;
				int l1 = super.height / 2 + 50;
				l1 += 20;
				if (super.clickType == 1 && super.clickX >= j1 - 75 && super.clickX <= j1 + 75
						&& super.clickY >= l1 - 20 && super.clickY <= l1 + 20) {
					anInt850 = 0;
					login(username, password, false);
					if (loggedIn)
						return;
				}
				j1 = super.width / 2 + 80;
				if (super.clickType == 1 && super.clickX >= j1 - 75 && super.clickX <= j1 + 75
						&& super.clickY >= l1 - 20 && super.clickY <= l1 + 20) {
					loginScreenState = 0;
					username = "";
					password = "";
				}
				do {
					int i2 = readCharacter();
					if (i2 == -1)
						break;
					boolean flag = false;
					for (int j2 = 0; j2 < VALID_CHARACTERS.length(); j2++) {
						if (i2 != VALID_CHARACTERS.charAt(j2))
							continue;
						flag = true;
						break;
					}

					if (anInt977 == 0) {
						if (i2 == 8 && username.length() > 0)
							username = username.substring(0, username.length() - 1);
						if (i2 == 9 || i2 == 10 || i2 == 13)
							anInt977 = 1;
						if (flag)
							username += (char) i2;
						if (username.length() > 12)
							username = username.substring(0, 12);
					} else if (anInt977 == 1) {
						if (i2 == 8 && password.length() > 0)
							password = password.substring(0, password.length() - 1);
						if (i2 == 9 || i2 == 10 || i2 == 13)
							anInt977 = 0;
						if (flag)
							password += (char) i2;
						if (password.length() > 20)
							password = password.substring(0, 20);
					}
				} while (true);
				return;
			}
			if (loginScreenState == 3) {
				int l = super.width / 2;
				int k1 = super.height / 2 + 50;
				k1 += 20;
				if (super.clickType == 1 && super.clickX >= l - 75 && super.clickX <= l + 75
						&& super.clickY >= k1 - 20 && super.clickY <= k1 + 20)
					loginScreenState = 0;
			}
		}
	}

	public void method150(int i, int j, int k, int l, int i1, int j1) {
		int k1 = currentScene.method267(j, k, i);
		i1 = 62 / i1;
		if (k1 != 0) {
			int l1 = currentScene.method271(j, k, i, k1);
			int k2 = l1 >> 6 & 3;
			int i3 = l1 & 0x1f;
			int k3 = j1;
			if (k1 > 0)
				k3 = l;
			int ai[] = minimapImage.pixels;
			int k4 = 24624 + k * 4 + (103 - i) * 512 * 4;
			int i5 = k1 >> 14 & 0x7fff;
			GameObjectDefinition class47_2 = GameObjectDefinition.getDefinition(i5);
			if (class47_2.anInt795 != -1) {
				IndexedImage class50_sub1_sub1_sub3_2 = aClass50_Sub1_Sub1_Sub3Array1153[class47_2.anInt795];
				if (class50_sub1_sub1_sub3_2 != null) {
					int i6 = (class47_2.sizeX * 4 - class50_sub1_sub1_sub3_2.width) / 2;
					int j6 = (class47_2.sizeY * 4 - class50_sub1_sub1_sub3_2.height) / 2;
					class50_sub1_sub1_sub3_2.drawImage(48 + k * 4 + i6, 48 + (104 - i - class47_2.sizeY) * 4 + j6
					);
				}
			} else {
				if (i3 == 0 || i3 == 2)
					if (k2 == 0) {
						ai[k4] = k3;
						ai[k4 + 512] = k3;
						ai[k4 + 1024] = k3;
						ai[k4 + 1536] = k3;
					} else if (k2 == 1) {
						ai[k4] = k3;
						ai[k4 + 1] = k3;
						ai[k4 + 2] = k3;
						ai[k4 + 3] = k3;
					} else if (k2 == 2) {
						ai[k4 + 3] = k3;
						ai[k4 + 3 + 512] = k3;
						ai[k4 + 3 + 1024] = k3;
						ai[k4 + 3 + 1536] = k3;
					} else if (k2 == 3) {
						ai[k4 + 1536] = k3;
						ai[k4 + 1536 + 1] = k3;
						ai[k4 + 1536 + 2] = k3;
						ai[k4 + 1536 + 3] = k3;
					}
				if (i3 == 3)
					if (k2 == 0)
						ai[k4] = k3;
					else if (k2 == 1)
						ai[k4 + 3] = k3;
					else if (k2 == 2)
						ai[k4 + 3 + 1536] = k3;
					else if (k2 == 3)
						ai[k4 + 1536] = k3;
				if (i3 == 2)
					if (k2 == 3) {
						ai[k4] = k3;
						ai[k4 + 512] = k3;
						ai[k4 + 1024] = k3;
						ai[k4 + 1536] = k3;
					} else if (k2 == 0) {
						ai[k4] = k3;
						ai[k4 + 1] = k3;
						ai[k4 + 2] = k3;
						ai[k4 + 3] = k3;
					} else if (k2 == 1) {
						ai[k4 + 3] = k3;
						ai[k4 + 3 + 512] = k3;
						ai[k4 + 3 + 1024] = k3;
						ai[k4 + 3 + 1536] = k3;
					} else if (k2 == 2) {
						ai[k4 + 1536] = k3;
						ai[k4 + 1536 + 1] = k3;
						ai[k4 + 1536 + 2] = k3;
						ai[k4 + 1536 + 3] = k3;
					}
			}
		}
		k1 = currentScene.method269(j, k, i);
		if (k1 != 0) {
			int i2 = currentScene.method271(j, k, i, k1);
			int l2 = i2 >> 6 & 3;
			int j3 = i2 & 0x1f;
			int l3 = k1 >> 14 & 0x7fff;
			GameObjectDefinition class47_1 = GameObjectDefinition.getDefinition(l3);
			if (class47_1.anInt795 != -1) {
				IndexedImage class50_sub1_sub1_sub3_1 = aClass50_Sub1_Sub1_Sub3Array1153[class47_1.anInt795];
				if (class50_sub1_sub1_sub3_1 != null) {
					int j5 = (class47_1.sizeX * 4 - class50_sub1_sub1_sub3_1.width) / 2;
					int k5 = (class47_1.sizeY * 4 - class50_sub1_sub1_sub3_1.height) / 2;
					class50_sub1_sub1_sub3_1.drawImage(48 + k * 4 + j5, 48 + (104 - i - class47_1.sizeY) * 4 + k5
					);
				}
			} else if (j3 == 9) {
				int l4 = 0xeeeeee;
				if (k1 > 0)
					l4 = 0xee0000;
				int ai1[] = minimapImage.pixels;
				int l5 = 24624 + k * 4 + (103 - i) * 512 * 4;
				if (l2 == 0 || l2 == 2) {
					ai1[l5 + 1536] = l4;
					ai1[l5 + 1024 + 1] = l4;
					ai1[l5 + 512 + 2] = l4;
					ai1[l5 + 3] = l4;
				} else {
					ai1[l5] = l4;
					ai1[l5 + 512 + 1] = l4;
					ai1[l5 + 1024 + 2] = l4;
					ai1[l5 + 1536 + 3] = l4;
				}
			}
		}
		k1 = currentScene.getFloorDecorationHash(j, k, i);
		if (k1 != 0) {
			int j2 = k1 >> 14 & 0x7fff;
			GameObjectDefinition class47 = GameObjectDefinition.getDefinition(j2);
			if (class47.anInt795 != -1) {
				IndexedImage class50_sub1_sub1_sub3 = aClass50_Sub1_Sub1_Sub3Array1153[class47.anInt795];
				if (class50_sub1_sub1_sub3 != null) {
					int i4 = (class47.sizeX * 4 - class50_sub1_sub1_sub3.width) / 2;
					int j4 = (class47.sizeY * 4 - class50_sub1_sub1_sub3.height) / 2;
					class50_sub1_sub1_sub3.drawImage(48 + k * 4 + i4, 48 + (104 - i - class47.sizeY) * 4 + j4);
				}
			}
		}
	}

	public void method151(int i) {
		anInt1138++;
		processPlayerAdditions(true);
		method57(751, true);
		processPlayerAdditions(false);
		method57(751, false);
		method51(false);
		method76(-992);
		if (!oriented) {
			int j = anInt1251;
			if (anInt1289 / 256 > j)
				j = anInt1289 / 256;
			if (aBooleanArray927[4] && anIntArray852[4] + 128 > j)
				j = anIntArray852[4] + 128;
			int l = cameraHorizontal + anInt1255 & 0x7ff;
			setCameraPosition(anInt1262, anInt1263, method110(localPlayer.worldY, localPlayer.worldX, (byte) 9,
					plane) - 50, j, l);
		}
		int k;
		if (!oriented)
			k = method117((byte) 1);
		else
			k = method118(-276);
		int i1 = cameraX;
		int j1 = cameraZ;
		int k1 = cameraY;
		int l1 = anInt1219;
		int i2 = anInt1220;
		if (i != 2)
			anInt1004 = incomingRandom.nextInt();
		for (int j2 = 0; j2 < 5; j2++)
			if (aBooleanArray927[j2]) {
				int k2 = (int) ((Math.random() * (double) (anIntArray1105[j2] * 2 + 1) - (double) anIntArray1105[j2]) + Math
						.sin((double) quakeTimes[j2] * ((double) anIntArray991[j2] / 100D))
						* (double) anIntArray852[j2]);
				if (j2 == 0)
					cameraX += k2;
				if (j2 == 1)
					cameraZ += k2;
				if (j2 == 2)
					cameraY += k2;
				if (j2 == 3)
					anInt1220 = anInt1220 + k2 & 0x7ff;
				if (j2 == 4) {
					anInt1219 += k2;
					if (anInt1219 < 128)
						anInt1219 = 128;
					if (anInt1219 > 383)
						anInt1219 = 383;
				}
			}

		int l2 = Rasterizer3D.anInt1547;
		Model.aBoolean1705 = true;
		Model.anInt1708 = 0;
		Model.anInt1706 = super.mouseX - 4;
		Model.anInt1707 = super.mouseY - 4;
		Rasterizer.resetPixels();
		currentScene.method280(cameraX, k, 0, cameraZ, cameraY, anInt1220, anInt1219);
		currentScene.method255();
		method121(false);
		method127(true);
		method65(l2);
		renderGameView();
		aClass18_1158.drawGraphics(4, 4, super.gameGraphics);
		cameraX = i1;
		cameraZ = j1;
		cameraY = k1;
		anInt1219 = l1;
		anInt1220 = i2;
	}

	public void method152() {
		for (int index = 0; index < currentSound; index++) {
			//if (soundDelay[index] <= 0) {
			boolean flag1 = false;
			try {
					Buffer stream = SoundTrack.data(sound[index], soundType[index]);
					new SoundPlayer(new ByteArrayInputStream(stream.buffer, 0, stream.currentPosition), soundVolume[index], soundDelay[index]);
					if (System.currentTimeMillis() + (long) (stream.currentPosition / 22) > aLong1172
							+ (long) (anInt1257 / 22)) {
						anInt1257 = stream.currentPosition;
						aLong1172 = System.currentTimeMillis();
						if (method116(stream.currentPosition, stream.buffer)) {
							anInt1272 = sound[index];
							anInt935 = soundType[index];
						} else {
							flag1 = true;
						}

				}
			} catch (Exception exception) {
				if (SignLink.reportError) {
					outBuffer.putOpcode(80);
					outBuffer.putShort(sound[index] & 0x7fff);
				} else {
					outBuffer.putOpcode(80);
					outBuffer.putShort(-1);
				}
			}
			if (!flag1 || soundDelay[index] == -5) {
				currentSound--;
				for (int j = index; j < currentSound; j++) {
					sound[j] = sound[j + 1];
					soundType[j] = soundType[j + 1];
					soundDelay[j] = soundDelay[j + 1];
					soundVolume[j] = soundVolume[j + 1];
				}

				index--;
			} else {
				soundDelay[index] = -5;
			}
			/*} else {
				soundDelay[index]--;
			}*/
		}
		if (previousSong > 0) {
			previousSong -= 20;
			if (previousSong < 0)
				previousSong = 0;
			if (previousSong == 0 && musicEnabled && !lowMemory) {
				nextSong = currentSong;
				songChanging = true;
				onDemandRequester.request(2, nextSong);
			}
		}
	}

}
