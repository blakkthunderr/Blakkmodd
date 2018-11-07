package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.RegenPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import examplemod.patches.AbstractCardEnum;

public class ReverseWounds
        extends CustomCard {
    public static final String ID = "ReverseWounds";
    public static final String NAME = "Reverse Wounds";
    public static final String DESCRIPTION = "Gain regen for every stack of Vulnerable you have +1. Exhaust.";
    public static final String IMG_PATH = "img/ReverseWounds.png";
    private static final int COST = 1;
    private static final int BLOCK_AMT = 6;


    public ReverseWounds() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.COMMON, CardTarget.SELF);
        this.baseMagicNumber = 0;
        this.magicNumber = this.baseMagicNumber;
        this.baseHeal = 1;
        this.heal = this.baseHeal;
        this.exhaust = true;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

            int i;

            if (p.hasPower(VulnerablePower.POWER_ID)) {
                this.magicNumber = (p.getPower(VulnerablePower.POWER_ID).amount);
            } else {
                this.magicNumber = 0;
            }
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new RegenPower(p, this.magicNumber+this.baseHeal), this.magicNumber+this.baseHeal));



    }


    @Override
    public AbstractCard makeCopy() {
        return new ReverseWounds();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.baseHeal = 2;
            this.rawDescription = "Gain regen for every stack of Vulnerable you have +2. Exhaust.";
            this.initializeDescription();

        }
    }
}
