package com.vartala.soulofw0lf.rpgapi;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.vartala.soulofw0lf.rpgapi.achievementapi.TitleAchievement;
import com.vartala.soulofw0lf.rpgapi.borderapi.BorderCheck;
import com.vartala.soulofw0lf.rpgapi.chatapi.*;
import com.vartala.soulofw0lf.rpgapi.classapi.RpgClasses;
import com.vartala.soulofw0lf.rpgapi.commandapi.UniqueCommands;
import com.vartala.soulofw0lf.rpgapi.enumapi.ClassName;
import com.vartala.soulofw0lf.rpgapi.enumapi.Spell;
import com.vartala.soulofw0lf.rpgapi.factionapi.FactionLevel;
import com.vartala.soulofw0lf.rpgapi.foodapi.CustomFood;
import com.vartala.soulofw0lf.rpgapi.foodapi.FoodListener;
import com.vartala.soulofw0lf.rpgapi.guiapi.ClickInvListener;
import com.vartala.soulofw0lf.rpgapi.guiapi.RpgClickInv;
import com.vartala.soulofw0lf.rpgapi.guildapi.GuildObject;
import com.vartala.soulofw0lf.rpgapi.guildapi.GuildRank;
import com.vartala.soulofw0lf.rpgapi.listenersapi.PoisonListener;
import com.vartala.soulofw0lf.rpgapi.loaders.*;
import com.vartala.soulofw0lf.rpgapi.minionapi.MinionEntity;
import com.vartala.soulofw0lf.rpgapi.mobcommandapi.MobCommand;
import com.vartala.soulofw0lf.rpgapi.mobcommandapi.MobEditingChatListener;
import com.vartala.soulofw0lf.rpgapi.mobcommandapi.SetBuilder;
import com.vartala.soulofw0lf.rpgapi.partyapi.LFGPlayer;
import com.vartala.soulofw0lf.rpgapi.partyapi.PartyGroup;
import com.vartala.soulofw0lf.rpgapi.playerapi.RpgPlayer;
import com.vartala.soulofw0lf.rpgapi.poisonapi.PoisonBuilder;
import com.vartala.soulofw0lf.rpgapi.poisonapi.PoisonTimeChecker;
import com.vartala.soulofw0lf.rpgapi.poisonapi.RpgPoison;
import com.vartala.soulofw0lf.rpgapi.spellapi.MagicSpell;
import com.vartala.soulofw0lf.rpgapi.sqlapi.SQLHandler;
import com.vartala.soulofw0lf.rpgapi.tradeapi.TradeCommandProcessor;
import com.vartala.soulofw0lf.rpgapi.tradeapi.TradeEventListener;
import com.vartala.soulofw0lf.rpgapi.tradeapi.TradeHandler;
import com.vartala.soulofw0lf.rpgapi.util.HelpFile;
import com.vartala.soulofw0lf.rpgapi.util.HelpPage;
import com.vartala.soulofw0lf.rpgapi.util.RPGLogging;
import com.vartala.soulofw0lf.rpgapi.warpsapi.RpgWarp;
import com.vartala.soulofw0lf.rpgapi.warpsapi.WarpBuilder;
import com.vartala.soulofw0lf.rpgapi.warpsapi.WarpSetBuilder;
import com.vartala.soulofw0lf.rpgapi.warpsapi.WarpSets;
import de.kumpelblase2.remoteentities.EntityManager;
import de.kumpelblase2.remoteentities.RemoteEntities;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;


import com.vartala.soulofw0lf.rpgapi.eventsapi.MapRightClickEvent;
import com.vartala.soulofw0lf.rpgapi.listenersapi.MapListener;
import com.vartala.soulofw0lf.rpgapi.listenersapi.playerLogIn;
import com.vartala.soulofw0lf.rpgapi.mapsapi.ScrollMap;
import com.vartala.soulofw0lf.rpgapi.util.PlayerUtil;
import org.bukkit.scheduler.BukkitRunnable;

//@author soulofwolf linksbro..
public class RpgAPI extends JavaPlugin implements Listener {
    //Plugins
    public static RpgAPI plugin;

    //
    //files
    public static YamlConfiguration playerConfig = YamlConfiguration.loadConfiguration(new File("plugins/RpgAPI/RpgPlayers.yml"));
    public static YamlConfiguration tradeConfig = YamlConfiguration.loadConfiguration(new File("plugins/RpgAPI/Trade.yml"));
    public static YamlConfiguration tradeLocaleConfig = YamlConfiguration.loadConfiguration(new File("plugins/RpgAPI/Locale/Trade.yml"));
    public static YamlConfiguration playerLocaleConfig = YamlConfiguration.loadConfiguration(new File("plugins/RpgAPI/Locale/RpgPlayers.yml"));
    public static YamlConfiguration localeConfig = YamlConfiguration.loadConfiguration(new File("plugins/RpgAPI/Locale.yml"));
    public static YamlConfiguration guildConfig = YamlConfiguration.loadConfiguration(new File("plugins/RpgAPI/Guilds.yml"));
    public static YamlConfiguration guildLocaleConfig = YamlConfiguration.loadConfiguration(new File("plugins/RpgAPI/Locale/Guilds.yml"));
    public static YamlConfiguration chatConfig = YamlConfiguration.loadConfiguration(new File("plugins/RpgAPI/RpgChat.yml"));
    public static YamlConfiguration chatLocaleConfig = YamlConfiguration.loadConfiguration(new File("plugins/RpgAPI/Locale/RpgChat.yml"));
    public static YamlConfiguration foodConfig = YamlConfiguration.loadConfiguration(new File("plugins/RpgAPI/Food.yml"));
    public static YamlConfiguration foodLocaleConfig = YamlConfiguration.loadConfiguration(new File("plugins/RpgAPI/Locale/Food.yml"));
    public static YamlConfiguration clickConfig = YamlConfiguration.loadConfiguration(new File("plugins/RpgAPI/Clicks.yml"));
    public static YamlConfiguration clickLocaleConfig = YamlConfiguration.loadConfiguration(new File("plugins/RpgAPI/Locale/Clicks.yml"));
    public static YamlConfiguration settingsConfig = YamlConfiguration.loadConfiguration(new File("plugins/RpgAPI/Settings.yml"));
    public static YamlConfiguration settingsLocaleConfig = YamlConfiguration.loadConfiguration(new File("plugins/RpgAPI/Locale/Settings.yml"));
    public static YamlConfiguration achievementConfig = YamlConfiguration.loadConfiguration(new File("plugins/RpgAPI/Achievements.yml"));
    public static YamlConfiguration achievementLocaleConfig = YamlConfiguration.loadConfiguration(new File("plugins/RpgAPI/Locale/Achievements.yml"));
    public static YamlConfiguration minionConfig = YamlConfiguration.loadConfiguration(new File("plugins/RpgAPI/Minions.yml"));
    public static YamlConfiguration minionLocaleConfig = YamlConfiguration.loadConfiguration(new File("plugins/RpgAPI/Locale/Minions.yml"));
    public static YamlConfiguration classConfig = YamlConfiguration.loadConfiguration(new File("plugins/RpgAPI/Classes.yml"));
    public static YamlConfiguration classLocaleConfig = YamlConfiguration.loadConfiguration(new File("plugins/RpgAPI/Locale/Classes.yml"));
    public static YamlConfiguration mobCommand = YamlConfiguration.loadConfiguration(new File("plugins/RpgAPI/MobCommands.yml"));
    public static YamlConfiguration mobLocaleCommand = YamlConfiguration.loadConfiguration(new File("plugins/RpgAPI/Locale/MobCommands.yml"));
    public static YamlConfiguration poisonCommand = YamlConfiguration.loadConfiguration(new File("plugins/RpgAPI/Poisons.yml"));
    public static YamlConfiguration poisonLocaleCommand = YamlConfiguration.loadConfiguration(new File("plugins/RpgAPI/Locale/Poisons.yml"));
    public static YamlConfiguration testPlayer = YamlConfiguration.loadConfiguration(new File("plugins/RpgAPI/RpgPlayer/TestPlayer.yml"));
    public static YamlConfiguration worldBorder = YamlConfiguration.loadConfiguration(new File("plugins/RpgAPI/RpgBorders.yml"));
    public static YamlConfiguration worldLocaleBorder = YamlConfiguration.loadConfiguration(new File("plugins/RpgAPI/Locale/RpgBorders.yml"));
    public static YamlConfiguration warpConfig = YamlConfiguration.loadConfiguration(new File("plugins/RpgAPI/WarpConfig.yml"));
    public static YamlConfiguration warpLocaleConfig = YamlConfiguration.loadConfiguration(new File("plugins/RpgAPI/Locale/WarpConfig.yml"));
    public static YamlConfiguration languageConfig = YamlConfiguration.loadConfiguration(new File("plugins/RpgAPI/RpgLanguages.yml"));
    public static YamlConfiguration languageLocaleConfig = YamlConfiguration.loadConfiguration(new File("plugins/RpgAPI/Locale/RpgLanguages.yml"));
    public static YamlConfiguration cityConfig = YamlConfiguration.loadConfiguration(new File("plugins/RpgAPI/RpgChat/RpgCities.yml"));
    public static YamlConfiguration cityLocaleConfig = YamlConfiguration.loadConfiguration(new File("plugins/RpgAPI/Locale/RpgCities.yml"));
    public static YamlConfiguration regionConfig = YamlConfiguration.loadConfiguration(new File("plugins/RpgAPI/RpgChat/RpgRegions.yml"));
    public static YamlConfiguration regionLocaleConfig = YamlConfiguration.loadConfiguration(new File("plugins/RpgAPI/Locale/RpgRegions.yml"));


    //Listeners
    public MapListener MapListen;
    public playerLogIn PlayerListener;
    public ClickInvListener clickListener;
    public TradeEventListener tradeListener;
    public ChatListener chatListener;
    public FoodListener foodListener;
    public PoisonListener poisonlistener;
    public MobEditingChatListener mobEditingChatListener;

    //plugin booleans
    public static boolean useMySql = false;
    public static boolean guildsOn = true;
    public static boolean chatOn = true;
    public static boolean poisonedEarthOn = true;
    public static boolean classesOn = true;
    public static boolean racesOn = true;
    public static boolean achievementsOn = true;
    public static boolean foodOn = true;
    public static boolean partyOn = true;
    public static boolean minionsOn = true;
    public static boolean clickOn = true;
    public static boolean friendsOn = true;
    public static boolean lobbyOn = true;
    public static boolean questOn = true;
    public static boolean spawnsOn = true;
    public static boolean warpsOn = true;
    public static boolean tradeOn = true;
    public static boolean borderOn = true;
    public static boolean reputationOn = true;
    public static boolean languagesOn = true;
    public static boolean duelsOn = true;


    //Utilities
    PlayerUtil PlayerUtility = new PlayerUtil(this);

    //Variables
    public static ArrayList<RpgClickInv> rpgClicks = new ArrayList<RpgClickInv>();
    public static Map<String, GuildObject> guilds = new HashMap<>();
    public static Map<String, PartyGroup> partys = new HashMap<>();
    public static Map<String, LFGPlayer> lookingForGroup = new HashMap<>();
    public static Map<String, GuildRank> guildRanks = new HashMap<>();
    public static Integer partyQue = 0;
    public static Map<Integer, MinionEntity> activeMinions = new HashMap<>();
    public static Map<String, FactionLevel> allFactions = new HashMap<>();
    public static Map<String, TitleAchievement> titleAchievs = new HashMap<>();
    public static Map<Spell, MagicSpell> allSpells = new HashMap<>();
    public static Map<String, RpgPlayer> rpgPlayers = new HashMap<>();
    public static Map<ClassName, RpgClasses> rpgClasses = new HashMap<>();
    public static List<ChatClass> chatClasses = new ArrayList<ChatClass>();
    public static Map<String, String> activeNicks = new HashMap<>();
    public static Map<String, CustomFood> foodItems = new HashMap<>();
    public static boolean rpgStyleFood = true;
    public static String dBUserName = "";
    public static String dBPassword = "";
    public static String dBEncoding = "";
    public static boolean uniCode = true;
    public static String dBURL = "";
    public static Logger logger = Logger.getLogger(RpgAPI.class.getName());
    public static SQLHandler sqlHandler = null;
    public static TradeHandler tradeHandler = null;
    public static List<String> commands = new ArrayList<String>();
    public static Map<String, MobCommand> minionCommands = new HashMap<>();
    public static EntityManager entityManager;
    public static Map<String, RpgPoison> rpgPoisons = new HashMap<>();

    public static Map<String, String> localeSettings = new HashMap<>();
    public static Map<String, String> commandSettings = new HashMap<>();
    public static Map<String, WarpSets> savedSets = new HashMap<>();
    public static Map<String, RpgWarp> savedWarps = new HashMap<>();
    public static List<ItemStack> warpItems = new ArrayList<ItemStack>();
    public static Map<String, List<String>> warpCds = new HashMap<>();
    public static String nameDisplays = "";
    public static Map<String, String> playerColors = new HashMap<>();
    public static Map<String, List<String>> languageCypher = new HashMap<>();
    public static Map<String, List<String>> languageKey = new HashMap<>();
    public static Map<String, List<String>> pluginCommand = new HashMap<>();
    public static Map<String, Integer> chatDistances = new HashMap<>();
    public static List<ChatRegions> chatRegions = new ArrayList<ChatRegions>();
    public static List<RpgCities> rpgCities = new ArrayList<RpgCities>();
    public static Map<String, String> chatRealNames = new HashMap<>();
    public static List<HelpFile> helpMap = new ArrayList<HelpFile>();
    public static Map<String, List<HelpFile>> helpPages = new HashMap<>();
    public static Map<Integer, HelpPage> helpDisplay = new HashMap<>();

    public static RpgAPI getInstance() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        //load the default config if it doesn't exist
        saveDefaultConfig();
        //set all config based booleans to check which plugin features are enabled
        useMySql = getConfig().getBoolean("Use Mysql");
        guildsOn = getConfig().getBoolean("Plugins.Guilds");
        chatOn = getConfig().getBoolean("Plugins.Chat");
        poisonedEarthOn = getConfig().getBoolean("Plugins.Poisoned Earth");
        classesOn = getConfig().getBoolean("Plugins.Classes");
        racesOn = getConfig().getBoolean("Plugins.Races");
        achievementsOn = getConfig().getBoolean("Plugins.Achievements");
        foodOn = getConfig().getBoolean("Plugins.Food");
        partyOn = getConfig().getBoolean("Plugins.Party");
        minionsOn = getConfig().getBoolean("Plugins.Minions");
        clickOn = getConfig().getBoolean("Plugins.Click");
        friendsOn = getConfig().getBoolean("Plugins.Friends");
        lobbyOn = getConfig().getBoolean("Plugins.Lobby");
        questOn = getConfig().getBoolean("Plugins.Quest");
        spawnsOn = getConfig().getBoolean("Plugins.Spawns");
        warpsOn = getConfig().getBoolean("Plugins.Warps");
        tradeOn = getConfig().getBoolean("Plugins.Trade");
        borderOn = getConfig().getBoolean("Plugins.Border");
        reputationOn = getConfig().getBoolean("Plugins.Reputation");
        languagesOn = getConfig().getBoolean("Plugins.Languages");

        //register global listeners
        Bukkit.getPluginManager().registerEvents(this, this);
        this.MapListen = new MapListener(this);
        this.PlayerListener = new playerLogIn(this);


        //grab database values if they should be used
        if (useMySql) {
            dBUserName = getConfig().getString("Mysql Database.User");
            dBPassword = getConfig().getString("Mysql Database.Password");
            dBURL = getConfig().getString("Mysql Database.URL");
            sqlHandler = new SQLHandler();
            if (!sqlHandler.initialise()) {
                RPGLogging.logSevere("Failed to initialize the SQL connection. Check connection settings in RPGAPI config files. Otherwise please disable mysql as storage system.");
            } else
                RPGLogging.logInfo("SQL connection initialised.");
        }

        /*
        default ymls and initial settings
         */

        //player config
        playerConfig = YamlConfiguration.loadConfiguration(new File("plugins/RpgAPI/RpgPlayers.yml"));
        playerLocaleConfig = YamlConfiguration.loadConfiguration(new File("plugins/RpgAPI/Locale/RpgPlayers.yml"));
        if (playerConfig.get("Active Nicks") == null) {
            playerConfig.set("Active Nicks.Sample Player", "Sample Nick");
            playerConfig.set("Player Colors.soulofw0lf", "&4");
        }
        for (String players : playerConfig.getConfigurationSection("Active Nicks").getKeys(false)) {
            String currentNick = playerConfig.getString("Active Nicks." + players);
            activeNicks.put(players, currentNick);
        }

        //default test player charactor sheet
        testPlayer = YamlConfiguration.loadConfiguration(new File("plugins/RpgAPI/RpgPlayer/TestPlayer.yml"));

        //locale config (only general localle commands and messages should go in here)
        localeConfig = YamlConfiguration.loadConfiguration(new File("plugins/RpgAPI/Locale.yml"));

        //settings config, i'm sure we'll use ti for something eventually
        settingsConfig = YamlConfiguration.loadConfiguration(new File("plugins/RpgAPI/Settings.yml"));
        settingsLocaleConfig = YamlConfiguration.loadConfiguration(new File("plugins/RpgAPI/Locale/Settings.yml"));
        if (settingsConfig.get("Settings") == null) {
            settingsConfig.set("Settings", "this file is used for all plugin settings!");
        }

        //default file structure for rpgplayers
        File f = new File("plugins/RpgAPI/RpgPLayers");
        File[] files = f.listFiles();
        if (!(files == null)) {
            for (File playerFile : files) {
                YamlConfiguration.loadConfiguration(playerFile);
            }
        }

        //TODO: use the following code to grab commands out of each locale file in their loaders.
        /*
        for (String command : RpgAPI.pluginPartLocaleConfig.getConfigurationSection("Commands").getKeys(false)) {
            RpgAPI.commandSection.put(command, RpgAPI.pluginPartLocaleConfig.getString("Commands." + command));
            RpgAPI.commands.add(RpgAPI.pluginPartLocaleConfig.getString("Commands." + command));
        }
         */

        /*
        all yml files to be loaded if they are turned on. sorted in alphabetic order for ease of reference
         */


        //check for achievements and load settings
        if (achievementsOn) {new AchievementLoader();}

        //check for border and load settings
        if (borderOn) {new BorderLoader();}

        //check for chat and load settings
        if (chatOn) {new ChatLoader(this);}

        //check for classes and load settings
        if (classesOn) {new RpgClassLoader();}

        //check for click and load settings
        if (clickOn) {new ClickLoader(this);}

        //check for food and load setting
        if (foodOn) {new FoodLoader(this);}

        //check for guilds and load settings
        if (guildsOn) {new GuildLoader();}

        //check for languages and load settings
        if (languagesOn) {new LanguageLoader();}

        //check for minions and load settings
        if (minionsOn) {new MinionLoader(this);}

        //check for poisoned earth and load settings
        if (poisonedEarthOn) {new PoisonLoader(this);}

        //check for trade and load settings
        if (tradeOn) {new TradeLoader(this);}

        //check for warp and load settings
        if (warpsOn) {new WarpLoader();}

        //TODO: testing line remove later
        commands.add("effect");

        if (localeConfig.get("Locale Settings") == null) {
            localeConfig.set("Locale Settings", "This file is used to set all language settings!");
            localeConfig.set("Translations.Error Message", "&F[&2Rpg API&F] &4This command should be ");
            localeConfig.set("Translations.Active Character", "&F[&4Rpg Player&F] &2Your Active Character Name is &6");
            //help Command
            localeConfig.set("General Commands.Help Command.Alias", "Help");
            localeConfig.set("General Commands.Help Command.Help Color", "&2");
            localeConfig.set("General Commands.Help Command.Description", "&fShow the different help Pages. &2Usage: &f/help page#");
        }
        try {
            playerConfig.save(new File("plugins/RpgAPI/RpgPlayers.yml"));
            localeConfig.save(new File("plugins/RpgAPI/Locale.yml"));
            testPlayer.save(new File("plugins/RpgAPI/RpgPlayer/TestPlayer.yml"));
            settingsConfig.save(new File("plugins/RpgAPI/Settings.yml"));
        } catch (IOException e) {
        }
        for (String s : localeConfig.getConfigurationSection("Translations").getKeys(false)) {
            localeSettings.put(s, localeConfig.getString("Translations." + s));
        }
        List<String> generalCommands = new ArrayList<String>();

        for (String command : localeConfig.getConfigurationSection("General Commands").getKeys(false)) {
            String commandRT = localeConfig.getString("General Commands." + command + ".Alias");
            String helpColor = localeConfig.getString("General Commands." + command + ".Help Color");
            String descText = localeConfig.getString("General Commands." + command + ".Description");
            HelpFile cmd = new HelpFile();
            cmd.setCmdAlias(commandRT);
            cmd.setAliasColor(helpColor);
            cmd.setDescription(descText);
            cmd.setHelpGroup("General Commands");
            helpMap.add(cmd);
            commands.add(commandRT);
            commandSettings.put(command, commandRT);
            generalCommands.add(commandRT);
        }
        pluginCommand.put("General Commands", generalCommands);

        for (String pName : playerConfig.getConfigurationSection("Player Colors").getKeys(false)) {
            playerColors.put(pName, playerConfig.getString("Player Colors." + pName));
        }
    }

    public static RpgPlayer getRp(String name) {
        String nick = activeNicks.get(name);
        RpgPlayer rp = rpgPlayers.get(nick);
        return rp;
    }

    public static RpgPlayer getRp(Player p) {
        return getRp(p.getName());
    }

    @Override
    public void onDisable() {
        LoadCities.ToFile();
        LoadRegions.ToFile();
        for (String thisWarp : RpgAPI.savedWarps.keySet()) {
            if (warpsOn) {
                WarpBuilder.SaveWarp(thisWarp);
                WarpSetBuilder.SaveSets();
            }
        }
        playerConfig.set("Player Colors", playerColors);
        try {
            if (warpsOn) {
                warpConfig.save(new File("plugins/RpgAPI/WarpConfig.yml"));
            }
            playerConfig.save(new File("plugins/RpgAPI/RpgPlayers.yml"));
        } catch (IOException e) {
        }

    }

    @EventHandler
    public void onRightClickMap(MapRightClickEvent event) {
        if (event.isPlayerSneaking()) {
            ScrollMap.getScrollMap(event.getMapID()).getMapRender().decrementIndex();
            ScrollMap.getScrollMap(event.getMapID()).update(event.getPlayer().getName());
        } else {
            ScrollMap.getScrollMap(event.getMapID()).getMapRender().incrementIndex();
            ScrollMap.getScrollMap(event.getMapID()).update(event.getPlayer().getName());
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void playerCommand(PlayerCommandPreprocessEvent event) {
        Player p = event.getPlayer();
        String s = event.getMessage();
        String[] args = s.split(" ");
        String cmdAlias = args[0].replaceAll("/", "");
        for (String command : commands) {
            if (cmdAlias.equalsIgnoreCase(command)) {
                event.setCancelled(true);
                UniqueCommands.BaseCommandHandler(p, args);

            }
        }
        // For testing Trading
        args[0] = args[0].replace("/", "");
        TradeCommandProcessor.process(p, args);
    }


}
