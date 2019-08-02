package terra.relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import terra.TerraMod;

public class SlaveCrownRelic extends CustomRelic {

    // ID, images, text.
    public static final String ID = TerraMod.makeID("SlaveCrownRelic");
    public static final String IMG = TerraMod.makePath(TerraMod.PLACEHOLDER_RELIC);
    public static final String OUTLINE = TerraMod.makePath(TerraMod.PLACEHOLDER_RELIC_OUTLINE);

    public SlaveCrownRelic() {
        super(
                ID,
                new Texture(IMG),
                new Texture(OUTLINE),
                RelicTier.STARTER,
                LandingSound.MAGICAL);
    }

    // Flash at the start of Battle.
    @Override
    public void atBattleStartPreDraw() {
        flash();
    }

    // Gain 1 energy on equip.
    @Override
    public void onEquip() {
        AbstractDungeon.player.energy.energyMaster += 1;
    }

    // Lose 1 energy on unequip.
    @Override
    public void onUnequip() {
        AbstractDungeon.player.energy.energyMaster -= 1;
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    // Which relic to return on making a copy of this relic.
    @Override
    public AbstractRelic makeCopy() {
        return new SlaveCrownRelic();
    }
}
