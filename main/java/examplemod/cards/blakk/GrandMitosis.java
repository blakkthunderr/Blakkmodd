package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import examplemod.patches.AbstractCardEnum;
import examplemod.powers.SoulPower;

import java.util.Iterator;

public class GrandMitosis
        extends CustomCard {
    public static final String ID = "GrandMitosis";
    public static final String NAME = "Grand Mitosis";
    public static final String DESCRIPTION = "Double your current Souls. Exhaust.";
    public static final String IMG_PATH = "img/GrandMitosis.png";
    private static final int COST = 2;



    public GrandMitosis() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {


        if (p.hasPower (SoulPower.POWER_ID)){
            this.magicNumber =  (p.getPower(SoulPower.POWER_ID).amount);
        }
        else{
            this.magicNumber = 0;

        }
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new SoulPower(p, this.magicNumber), this.magicNumber));





    }

    @Override
    public AbstractCard makeCopy() {
        return new GrandMitosis();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(1);

        }
    }
}
