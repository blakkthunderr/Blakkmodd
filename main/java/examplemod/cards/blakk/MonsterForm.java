package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import examplemod.patches.AbstractCardEnum;
import examplemod.powers.LeakingPower;
import examplemod.powers.SoulPower;
import examplemod.powers.WitchProtectionPower;

public class MonsterForm
        extends CustomCard {
    public static final String ID = "MonsterForm";
    public static final String NAME = "Monster Form";
    public static final String DESCRIPTION = "Gain !M! Souls. At the end of your turns, lose 1 Soul.";
    public static final String IMG_PATH = "img/MonsterForm.png";
    private static final int COST = 3;



    public MonsterForm() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.POWER, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 7;
        this.magicNumber = this.baseMagicNumber;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new LeakingPower(p, 1), 1));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new SoulPower(p, this.magicNumber), this.magicNumber));



    }

    @Override
    public AbstractCard makeCopy() {
        return new MonsterForm();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(3);

        }
    }
}
