package terra.helpers;

public enum TerraFilePaths {
    // Energy
    ENERGY_SYMBOL("img/terra_energy_symbol.png"),
    ENERGY_ORB("img/512/terra_energy_orb.png"),
    ENERGY_ORB_PORTRAIT("img/1024/terra_energy_orb_p.png"),
    ENERGY_ORB_VFX("img/terra_energy_orb_vfx.png"),

    // Small Cards
    ATTACK_CARD("img/512/terra_attack.png"),
    ATTACK_MAGIC_ADDON("img/512/terra_attack_magic.png"),
    SKILL_CARD("img/512/terra_skill.png"),
    SKILL_MAGIC_ADDON("img/512/terra_skill_magic.png"),
    POWER_CARD("img/512/terra_power.png"),
    POWER_MAGIC_ADDON("img/512/terra_power.png"),

    // Large Cards
    ATTACK_CARD_PORTRAIT("img/1024/terra_attack_p.png"),
    ATTACK_MAGIC_ADDON_PORTRAIT("img/1024/terra_attack_magic_p.png"),
    SKILL_CARD_PORTRAIT("img/1024/terra_skill_p.png"),
    SKILL_MAGIC_ADDON_PORTRAIT("img/1024/terra_skill_magic_p.png"),
    POWER_CARD_PORTRAIT("img/1024/terra_power_p.png"),
    POWER_MAGIC_ADDON_PORTRAIT("img/1024/terra_power_p.png"),

    // Character Selection
    CHAR_BUTTON("img/charSelect/button.png"),
    CHAR_PORTRAIT("img/charSelect/portrait.png"),

    // Character
    CORPSE("img/char/corpse.png"),
    SHOULDER_1("img/char/shoulder1.png"),
    SHOULDER_2("img/char/shoulder2.png"),
    ANIMATION("img/char/idle/Animation.scml"),
    SKELETON("img/char/skeleton.json"),
    ORB_VFX("img/char/orb/vfx.png"),

    // Orbs / Espers
    ORB_ESPER("img/mana/terra_orb_esper.png"),

    // Relics
    RELIC_SLAVE_CROWN("img/relics/terra_slave_crown.png"),
    RELIC_SLAVE_CROWN_OUTLINE("img/relics/terra_slave_crown_ol.png"),
    RELIC_DUALCAST("img/relics/terra_dualcast.png"),
    RELIC_DUALCAST_OUTLINE("img/relics/terra_dualcast_ol.png"),

    // JSON strings
    CARD_STRINGS("localization/TerraCards.json"),
    CHARACTER_STRINGS("localization/TerraCharacter.json"),
    RELIC_STRINGS("localization/TerraRelics.json");

    // Get File Path
    private final String filePath;

    TerraFilePaths(final String filePath) { this.filePath = filePath; }

    public String getFilePath() { return filePath; }
}
