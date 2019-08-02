package terra;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import basemod.BaseMod;
import basemod.ModLabel;
import basemod.ModPanel;
import basemod.helpers.RelicType;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditKeywordsSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.PostInitializeSubscriber;

import terra.potions.*;
import terra.patches.*;
import terra.relics.*;
import terra.variables.*;
import terra.cards.*;
import terra.characters.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
 * With that out of the way:
 * Welcome to this mildly over-commented Slay the Spire modding base. 
 * Use it to make your own mod of any type. - If you want to add any standard in-game content (Character, 
 * cards, relics), this is a good starting point.
 * It features 1 character with a minimal set of things: 1 card of each type, 1 debuff, 1 relic, etc
 * If you're new to modding, you basically *need* the BaseMod wiki for whatever you wish to add
 * https://github.com/daviscook477/BaseMod/wiki - work your way through with this base.
 * Feel free to use this in any way you like, of course. Happy modding!
 */

@SpireInitializer
public class TerraMod implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        PostInitializeSubscriber {

    public static final Logger logger = LogManager.getLogger(TerraMod.class.getName());

    //This is for the in-game mod settings panel.
    private static final String MODNAME = "The Esper";
    private static final String AUTHOR = "Reulan";
    private static final String DESCRIPTION = "Terra, the Esper.";

    // =============== INPUT TEXTURE LOCATION =================

    // Colors (RGB)
        // Character Color
        public static final Color TERRA_GREEN = CardHelper.getColor(64.0f, 70.0f, 70.0f);

        // Potion Colors in RGB
        public static final Color PLACEHOLDER_POTION_LIQUID = CardHelper.getColor(209.0f, 53.0f, 18.0f); // Orange-ish Red
        public static final Color PLACEHOLDER_POTION_HYBRID = CardHelper.getColor(255.0f, 230.0f, 230.0f); // Near White
        public static final Color PLACEHOLDER_POTION_SPOTS = CardHelper.getColor(100.0f, 25.0f, 10.0f); // Super Dark Red/Brown
        
    // Image folder name - This is where your image folder is.
    // This is good practice in case you ever need to move/rename it without screwing up every single path.
    // In this case, it's resources/defaultModResources/images (and then, say, /cards/Strike.png).

    private static final String DEFAULT_MOD_ASSETS_FOLDER = "defaultModResources/images";

    // Card backgrounds
    private static final String ATTACK_TERRA_GREEN = "512/bg_attack_default_gray.png";
    private static final String POWER_TERRA_GREEN = "512/bg_power_default_gray.png";
    private static final String SKILL_TERRA_GREEN = "512/bg_skill_default_gray.png";
    private static final String ENERGY_ORB_TERRA_GREEN = "512/card_default_gray_orb.png";
    private static final String CARD_ENERGY_ORB = "512/card_small_orb.png";

    private static final String ATTACK_TERRA_GREEN_PORTRAIT = "1024/bg_attack_default_gray.png";
    private static final String POWER_TERRA_GREEN_PORTRAIT = "1024/bg_power_default_gray.png";
    private static final String SKILL_TERRA_GREEN_PORTRAIT = "1024/bg_skill_default_gray.png";
    private static final String ENERGY_ORB_TERRA_GREEN_PORTRAIT = "1024/card_default_gray_orb.png";

    // Card images
    public static final String DEFAULT_COMMON_ATTACK = "cards/Attack.png";
    public static final String DEFAULT_COMMON_SKILL = "cards/Skill.png";
    public static final String DEFAULT_COMMON_POWER = "cards/Power.png";
    public static final String DEFAULT_UNCOMMON_ATTACK = "cards/Attack.png";
    public static final String DEFAULT_UNCOMMON_SKILL = "cards/Skill.png";
    public static final String DEFAULT_UNCOMMON_POWER = "cards/Power.png";
    public static final String DEFAULT_RARE_ATTACK = "cards/Attack.png";
    public static final String DEFAULT_RARE_SKILL = "cards/Skill.png";
    public static final String DEFAULT_RARE_POWER = "cards/Power.png";

    // Power images
    public static final String COMMON_POWER = "powers/placeholder_power.png";
    public static final String UNCOMMON_POWER = "powers/placeholder_power.png";
    public static final String RARE_POWER = "powers/placeholder_power.png";

    // Relic images  
    public static final String PLACEHOLDER_RELIC = "relics/placeholder_relic.png";
    public static final String PLACEHOLDER_RELIC_OUTLINE = "relics/outline/placeholder_relic.png";

    public static final String PLACEHOLDER_RELIC_2 = "relics/placeholder_relic2.png";
    public static final String PLACEHOLDER_RELIC_OUTLINE_2 = "relics/outline/placeholder_relic2.png";
    
    // Character assets
    private static final String THE_DEFAULT_BUTTON = "charSelect/DefaultCharacterButton.png";
    private static final String THE_DEFAULT_PORTRAIT = "charSelect/DefaultCharacterPortraitBG.png";
    public static final String THE_DEFAULT_SHOULDER_1 = "char/defaultCharacter/shoulder.png";
    public static final String THE_DEFAULT_SHOULDER_2 = "char/defaultCharacter/shoulder2.png";
    public static final String THE_DEFAULT_CORPSE = "char/defaultCharacter/corpse.png";

    //Mod Badge
    public static final String BADGE_IMAGE = "Badge.png";

    // Animations atlas and JSON files
    public static final String THE_DEFAULT_SKELETON_ATLAS = "char/defaultCharacter/skeleton.atlas";
    public static final String THE_DEFAULT_SKELETON_JSON = "char/defaultCharacter/skeleton.json";

    // =============== /INPUT TEXTURE LOCATION/ =================

    // =============== IMAGE PATHS =================

    // This is the command that will link up your core assets folder (line 89) ("defaultModResources/images")
    // together with the card image (everything above) ("cards/Attack.png") and it puts a "/" between them.
    // When adding a card image, you can, in fact, just do "defaultModResources/images/cards/Attack.png" in the actual card file.
    // This however, is good practice in case you want to change your "/images" folder at any point in time.

    /**
     * @param resource the resource, must *NOT* have a leading "/"
     * @return the full path
     */
    public static final String makePath(String resource) {
        return DEFAULT_MOD_ASSETS_FOLDER + "/" + resource;
    }

    // =============== /IMAGE PATHS/ =================

    // =============== SUBSCRIBE, CREATE THE COLOR, INITIALIZE =================

    public TerraMod() {
        logger.info("Subscribe to BaseMod hooks");

        BaseMod.subscribe(this);

        logger.info("Done subscribing");

        logger.info("Creating the color " + AbstractCardEnum.TERRA_GREEN.toString());

        BaseMod.addColor(AbstractCardEnum.TERRA_GREEN, TERRA_GREEN, TERRA_GREEN, TERRA_GREEN,
                TERRA_GREEN, TERRA_GREEN, TERRA_GREEN, TERRA_GREEN, makePath(ATTACK_TERRA_GREEN),
                makePath(SKILL_TERRA_GREEN), makePath(POWER_TERRA_GREEN),
                makePath(ENERGY_ORB_TERRA_GREEN), makePath(ATTACK_TERRA_GREEN_PORTRAIT),
                makePath(SKILL_TERRA_GREEN_PORTRAIT), makePath(POWER_TERRA_GREEN_PORTRAIT),
                makePath(ENERGY_ORB_TERRA_GREEN_PORTRAIT), makePath(CARD_ENERGY_ORB));

        logger.info("Done creating the color");
    }

    @SuppressWarnings("unused")
    public static void initialize() {
        logger.info("========================= Initializing Default Mod. Hi. =========================");
        TerraMod defaultmod = new TerraMod();
        logger.info("========================= /Default Mod Initialized/ =========================");
    }

    // ============== /SUBSCRIBE, CREATE THE COLOR, INITIALIZE/ =================

    
    // =============== LOAD THE CHARACTER =================

    @Override
    public void receiveEditCharacters() {
        logger.info("Beginning to edit characters. " + "Add " + TerraEnum.THE_DEFAULT.toString());

        BaseMod.addCharacter(new Terra("the Default", TerraEnum.THE_DEFAULT),
                makePath(THE_DEFAULT_BUTTON), makePath(THE_DEFAULT_PORTRAIT), TerraEnum.THE_DEFAULT);
        
        receiveEditPotions();
        logger.info("Done editing characters");
    }

    // =============== /LOAD THE CHARACTER/ =================

    
    // =============== POST-INITIALIZE =================

    
    @Override
    public void receivePostInitialize() {

        logger.info("Loading badge image and mod options");
        // Load the Mod Badge
        Texture badgeTexture = new Texture(makePath(BADGE_IMAGE));
        
        // Create the Mod Menu
        ModPanel settingsPanel = new ModPanel();
        settingsPanel.addUIElement(new ModLabel("TerraMod doesn't have any settings!", 400.0f, 700.0f,
                settingsPanel, (me) -> {
        }));
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);

        logger.info("Done loading badge Image and mod options");

       }

    // =============== / POST-INITIALIZE/ =================

    
    // ================ ADD POTIONS ===================

       
    public void receiveEditPotions() {
        logger.info("Beginning to edit potions");
       
        // Class Specific Potion. If you want your potion to not be class-specific, just remove the player class at the end (in this case the "TerraEnum.THE_DEFAULT")
        BaseMod.addPotion(ElixirPotion.class, PLACEHOLDER_POTION_LIQUID, PLACEHOLDER_POTION_HYBRID, PLACEHOLDER_POTION_SPOTS, ElixirPotion.POTION_ID, TerraEnum.THE_DEFAULT);
      
        logger.info("Done editing potions");
    }
    
    // ================ /ADD POTIONS/ ===================

    
    // ================ ADD RELICS ===================

    @Override
    public void receiveEditRelics() {
        logger.info("Adding relics");

        // This adds a character specific relic. Only when you play with the mentioned color, will you get this relic.
        BaseMod.addRelicToCustomPool(new SlaveCrownRelic(), AbstractCardEnum.TERRA_GREEN);

        // This adds a relic to the Shared pool. Every character can find this relic.
        BaseMod.addRelic(new SoulOfThamasRelic(), RelicType.SHARED);

        logger.info("Done adding relics!");
    }

    // ================ /ADD RELICS/ ===================

    
    
    // ================ ADD CARDS ===================

    @Override
    public void receiveEditCards() {
        logger.info("Adding variables");
        // Add the Custom Dynamic Variables
        BaseMod.addDynamicVariable(new EsperCounter());
        
        logger.info("Adding cards");
        // Add the cards
        BaseMod.addCard(new DefaultCommonAttack());
        BaseMod.addCard(new DefaultAttackWithVariable());
        BaseMod.addCard(new DefaultCommonSkill());
        BaseMod.addCard(new DefaultCommonPower());
        BaseMod.addCard(new DefaultUncommonSkill());
        BaseMod.addCard(new DefaultUncommonAttack());
        BaseMod.addCard(new DefaultUncommonPower());
        BaseMod.addCard(new DefaultRareAttack());
        BaseMod.addCard(new DefaultRareSkill());
        BaseMod.addCard(new DefaultRarePower());

        logger.info("Making sure the cards are unlocked.");
        // Unlock the cards
        UnlockTracker.unlockCard(DefaultCommonAttack.ID);
        UnlockTracker.unlockCard(DefaultAttackWithVariable.ID);
        UnlockTracker.unlockCard(DefaultCommonSkill.ID);
        UnlockTracker.unlockCard(DefaultCommonPower.ID);
        UnlockTracker.unlockCard(DefaultUncommonSkill.ID);
        UnlockTracker.unlockCard(DefaultUncommonAttack.ID);
        UnlockTracker.unlockCard(DefaultUncommonPower.ID);
        UnlockTracker.unlockCard(DefaultRareAttack.ID);
        UnlockTracker.unlockCard(DefaultRareSkill.ID);
        UnlockTracker.unlockCard(DefaultRarePower.ID);

        logger.info("Done adding cards!");
    }

    // ================ /ADD CARDS/ ===================

    
    
    // ================ LOAD THE TEXT ===================

    @Override
    public void receiveEditStrings() {
        logger.info("Beginning to edit strings");

        // CardStrings
        BaseMod.loadCustomStringsFile(CardStrings.class,
                "defaultModResources/localization/TerraMod-Card-Strings.json");

        // PowerStrings
        BaseMod.loadCustomStringsFile(PowerStrings.class,
                "defaultModResources/localization/TerraMod-Power-Strings.json");

        // RelicStrings
        BaseMod.loadCustomStringsFile(RelicStrings.class,
                "defaultModResources/localization/TerraMod-Relic-Strings.json");

        // PotionStrings
        BaseMod.loadCustomStringsFile(PotionStrings.class,
                "defaultModResources/localization/TerraMod-Potion-Strings.json");

        logger.info("Done editing strings");
    }

    // ================ /LOAD THE TEXT/ ===================

    // ================ LOAD THE KEYWORDS ===================

    @Override
    public void receiveEditKeywords() {
        final String[] placeholder = { "keyword", "keywords", "Keyword", "Keywords" };
        BaseMod.addKeyword(placeholder, "Whenever you play a card, gain 1 dexterity this turn only.");

    }

    // ================ /LOAD THE KEYWORDS/ ===================    

    // this adds "ModName:" before the ID of any card/relic/power etc.
    // in order to avoid conflicts if any other mod uses the same ID.
    }

}
