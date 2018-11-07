package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import examplemod.patches.AbstractCardEnum;

public class HardSteel
        extends CustomCard {
    public static final String ID = "HardSteel";
    public static final String NAME = "Hard Steel";
    public static final String DESCRIPTION = "Gain !B! block. Gain !M! Plated Armor.";
    public static final String IMG_PATH = "img/HardSteel.png";
    private static final int COST = 2;
    private static final int BLOCK_AMT = 10;


    public HardSteel() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 10;
        this.block = this.baseBlock;
        this.magicNumber = this.baseMagicNumber = 3;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PlatedArmorPower(p, this.magicNumber), this.magicNumber));


    }


    @Override
    public AbstractCard makeCopy() {
        return new HardSteel();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(3);
            this.upgradeMagicNumber(1);

        }
    }
}
