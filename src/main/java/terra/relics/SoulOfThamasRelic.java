package terra.relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import terra.TerraMod;

public class SoulOfThamasRelic extends CustomRelic {

    // ID, images, text.
    public static final String ID = TerraMod.makeID("SoulOfThamasRelic");
    public static final String IMG = TerraMod.makePath(TerraMod.PLACEHOLDER_RELIC_2);
    public static final String OUTLINE = TerraMod.makePath(TerraMod.PLACEHOLDER_RELIC_OUTLINE_2);

    public SoulOfThamasRelic() {
        super(
                ID,
                new Texture(IMG),
                new Texture(OUTLINE),
                RelicTier.COMMON,
                LandingSound.MAGICAL);
    }

    // Gain 1 Strength on on equip.
    @Override
    public void atBattleStart() {
        this.flash();
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 1), 1));
        AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    // Which relic to return on making a copy of this relic.
    @Override
    public AbstractRelic makeCopy() {
        return new SoulOfThamasRelic();
    }
}
