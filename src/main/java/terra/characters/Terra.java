package terra.characters;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import basemod.abstracts.CustomPlayer;
import basemod.animations.SpriterAnimation;
import terra.TerraMod;
import terra.patches.AbstractCardEnum;
import terra.relics.SlaveCrownRelic;

//Wiki-page https://github.com/daviscook477/BaseMod/wiki/Custom-Characters
//and https://github.com/daviscook477/BaseMod/wiki/Migrating-to-5.0

public class Terra extends CustomPlayer {
    public static final Logger logger = LogManager.getLogger(TerraMod.class.getName());

    public static final int ENERGY_PER_TURN = 3;
    public static final int STARTING_HP = 75;
    public static final int MAX_HP = 75;
    public static final int STARTING_GOLD = 99;
    public static final int CARD_DRAW = 9;
    public static final int ORB_SLOTS = 0;

    public static final String[] orbTextures = {
            "defaultModResources/images/char/defaultCharacter/orb/layer1.png",
            "defaultModResources/images/char/defaultCharacter/orb/layer2.png",
            "defaultModResources/images/char/defaultCharacter/orb/layer3.png",
            "defaultModResources/images/char/defaultCharacter/orb/layer4.png",
            "defaultModResources/images/char/defaultCharacter/orb/layer5.png",
            "defaultModResources/images/char/defaultCharacter/orb/layer6.png",
            "defaultModResources/images/char/defaultCharacter/orb/layer1d.png",
            "defaultModResources/images/char/defaultCharacter/orb/layer2d.png",
            "defaultModResources/images/char/defaultCharacter/orb/layer3d.png",
            "defaultModResources/images/char/defaultCharacter/orb/layer4d.png",
            "defaultModResources/images/char/defaultCharacter/orb/layer5d.png", };
    
    // Textures, Energy Orb configuration, starting skills + relics, Animations
    public Terra(String name, PlayerClass setClass) {
        super(name,
                setClass,
                orbTextures,
                terra.helpers.TerraFilePaths.ORB_VFX.getFilePath(),
                null,
                new SpriterAnimation(
                        "defaultModResources/images/char/defaultCharacter/Spriter/theDefaultAnimation.scml"));

        initializeClass(null, // required call to load textures and setup energy/loadout
                terra.helpers.TerraFilePaths.SHOULDER_1.getFilePath(), // campfire
                terra.helpers.TerraFilePaths.SHOULDER_2.getFilePath(), // campfire 2
                terra.helpers.TerraFilePaths.CORPSE.getFilePath(),
                getLoadout(),
                20.0F,
                -10.0F,
                220.0F,
                290.0F,
                new EnergyManager(ENERGY_PER_TURN));

        this.loadAnimation(
                terra.helpers.TerraFilePaths.ANIMATION.getFilePath(),
                terra.helpers.TerraFilePaths.SKELETON.getFilePath(),
                1.0f);
        AnimationState.TrackEntry e = this.state.setAnimation(
                0,
                "animation",
                true);
        e.setTime(e.getEndTime() * MathUtils.random());

        this.dialogX = (this.drawX + 0.0F * Settings.scale);
        this.dialogY = (this.drawY + 220.0F * Settings.scale);
    }

    // Starting description and loadout
    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo("The Esper",
                "Magically barrage enemies with Fire, while summoning Espers to control the battlefield. NL " + "Terra can also swiftly dispatch her foes with her spellblade. ",
                STARTING_HP,
                MAX_HP,
                ORB_SLOTS,
                STARTING_GOLD,
                CARD_DRAW,
                this,
                getStartingRelics(),
                getStartingDeck(), false);
    }

    // Starting Deck
    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();

        logger.info("Begin loading starter Deck Strings");

        retVal.add(Strike_Terra.ID);
        retVal.add(Strike_Terra.ID);
        retVal.add(Strike_Terra.ID);
        retVal.add(Strike_Terra.ID);
        retVal.add(Defend_Terra.ID);
        retVal.add(Defend_Terra.ID);
        retVal.add(Defend_Terra.ID);
        retVal.add(Defend_Terra.ID);
        retVal.add(Attack_Terra.ID);
        retVal.add(Fire.ID);
        return retVal;
    }

    // Starting Relics	
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(SlaveCrownRelic.ID);
        UnlockTracker.markRelicAsSeen(SlaveCrownRelic.ID);
        return retVal;
    }

    // Character Select screen effect
    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("ATTACK_DAGGER_1", 1.25f); // Sound Effect
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT,
                false); // Screen Effect
    }

    // Character Select on-button-press sound effect
    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_DAGGER_1";
    }

    // Should return how much HP your maximum HP reduces by when starting a run at
    // Ascension 14 or higher. (ironclad loses 5, defect and silent lose 4 hp respectively)
    @Override
    public int getAscensionMaxHPLoss() {
        return 0;
    }

    // Should return the card color enum to be associated with your character.
    @Override
    public AbstractCard.CardColor getCardColor() {
        return AbstractCardEnum.TERRA_GREEN;
    }

    // Should return a color object to be used to color the trail of moving cards
    @Override
    public Color getCardTrailColor() {
        return TerraMod.TERRA_GREEN;
    }

    // Should return a BitmapFont object that you can use to customize how your
    // energy is displayed from within the energy orb.
    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    // Should return class name as it appears in run history screen.
    @Override
    public String getLocalizedCharacterName() {
        return "The Esper";
    }

    //Which card should be obtainable from the Match and Keep event?
    @Override
    public AbstractCard getStartCardForEvent() {
        return new Fire();
    }

    // The class name as it appears next to your player name in-game
    @Override
    public String getTitle(AbstractPlayer.PlayerClass playerClass) {
        return "the Esper";
    }

    // Should return a new instance of your character, sending this.name as its name parameter.
    @Override
    public AbstractPlayer newInstance() {
        return new Terra(this.name, this.chosenClass);
    }

    // Should return a Color object to be used to color the miniature card images in run history.
    @Override
    public Color getCardRenderColor() { return TerraMod.TERRA_GREEN; }

    // Should return a Color object to be used as screen tint effect when your
    // character attacks the heart.
    @Override
    public Color getSlashAttackColor() {
        return TerraMod.TERRA_GREEN;
    }

    // Should return an AttackEffect array of any size greater than 0. These effects
    // will be played in sequence as your character's finishing combo on the heart.
    // Attack effects are the same as used in DamageAction and the like.
    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[] {
                AbstractGameAction.AttackEffect.BLUNT_HEAVY };
    }

    // Should return a string containing what text is shown when your character is
    // about to attack the heart. For example, the defect is "NL You charge your
    // core to its maximum..."
    @Override
    public String getSpireHeartText() {
        return "You touch the heart.";
    }

    // The vampire events refer to the base game characters as "brother", "sister",
    // and "broken one" respectively.This method should return a String containing
    // the full text that will be displayed as the first screen of the vampires event.
    @Override
    public String getVampireText() {
        return "Navigating an unlit street, you come across several hooded figures in the midst of some dark ritual. As you approach, they turn to you in eerie unison. The tallest among them bares fanged teeth and extends a long, pale hand towards you. NL ~\"Join~ ~us~ ~basic~ ~one,~ ~and~ ~feel~ ~the~ ~warmth~ ~of~ ~the~ ~Spire.\"~";
    }
}
