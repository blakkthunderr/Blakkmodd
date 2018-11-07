package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import examplemod.patches.AbstractCardEnum;
import examplemod.powers.SoulPower;

public class IronMaiden
        extends CustomCard {
    public static final String ID = "IronMaiden";
    public static final String NAME = "Iron Maiden";
    public static final String DESCRIPTION = "Gain 3 Weak and 3 Vulnerable. Gain !M! Thorns.";
    public static final String IMG_PATH = "img/IronMaiden.png";
    private static final int COST = 2;



    public IronMaiden() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.POWER, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 6;
        this.magicNumber = this.baseMagicNumber;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ThornsPower(p, this.magicNumber), this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new WeakPower(p, 3,true), 3));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new VulnerablePower(p, 3,true), 3));



    }

    @Override
    public AbstractCard makeCopy() {
        return new IronMaiden();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(3);
            this.initializeDescription();

        }
    }
}
