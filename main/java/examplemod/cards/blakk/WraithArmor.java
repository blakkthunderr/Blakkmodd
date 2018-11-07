package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.IntangiblePower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import examplemod.actions.ModifyCostAction;
import examplemod.patches.AbstractCardEnum;

public class WraithArmor
        extends CustomCard {
    public static final String ID = "WraithArmor";
    public static final String NAME = "Wraith Armor";
    public static final String DESCRIPTION = "Gain 1 Intangible. Whenever you play this card, increase its cost by 1. Ethereal.";
    public static final String IMG_PATH = "img/WraithArmor.png";
    private static final int COST = 1;
    private static final int BLOCK_AMT = 5;


    public WraithArmor() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.RARE, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 1;
        this.isEthereal = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, this.magicNumber), this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ModifyCostAction(this,+1));

    }


    @Override
    public AbstractCard makeCopy() {
        return new WraithArmor();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);

        }
    }
}
