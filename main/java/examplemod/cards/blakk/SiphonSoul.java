package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import examplemod.patches.AbstractCardEnum;
import examplemod.powers.SoulPower;

public class SiphonSoul
        extends CustomCard {
    public static final String ID = "SiphonSoul";
    public static final String NAME = "Siphon Soul";
    public static final String DESCRIPTION = "Gain 2 Souls. The enemy loses !M! Strength. Exhaust.";
    public static final String IMG_PATH = "img/SiphonSoul.png";
    private static final int COST = 2;
    private static final int BLOCK_AMT = 6;


    public SiphonSoul() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;



        this.exhaust = true;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {


                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new SoulPower(p, 2), 2));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new StrengthPower(m, -this.magicNumber), -this.magicNumber));




    }


    @Override
    public AbstractCard makeCopy() {
        return new SiphonSoul();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(2);


        }
    }
}
