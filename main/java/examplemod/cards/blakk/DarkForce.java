package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import examplemod.patches.AbstractCardEnum;

public class DarkForce
        extends CustomCard {
    public static final String ID = "DarkForce";
    public static final String NAME = "Dark Force";
    public static final String DESCRIPTION = "If you are Weak, gain !M! Strength. Exhaust.";
    public static final String IMG_PATH = "img/DarkForce.png";
    private static final int COST = 1;
    //private static final int BLOCK_AMT = 6;


    public DarkForce() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.COMMON, CardTarget.SELF);
        this.baseMagicNumber = 2;

        this.magicNumber=this.baseMagicNumber;

        this.exhaust = true;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {



        if (p.hasPower(WeakPower.POWER_ID)) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber));

        }

    }


    @Override
    public AbstractCard makeCopy() {
        return new DarkForce();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);

        }
    }
}
