package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import examplemod.actions.ModifyCostAction;
import examplemod.actions.ModifyMagicNumberAction;
import examplemod.patches.AbstractCardEnum;

public class VortexShield
        extends CustomCard {
    public static final String ID = "VortexShield";
    public static final String NAME = "Vortex Shield";
    public static final String DESCRIPTION = "Gain !B! block. Gain !M! [E].  Increase this card's cost and energy gain by 1 every time it is played.";
    public static final String IMG_PATH = "img/VortexShield.png";
    private static final int COST = 0;
    private static final int BLOCK_AMT = 5;


    public VortexShield() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 8;
        this.magicNumber = this.baseMagicNumber = 0;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));

        AbstractDungeon.actionManager.addToBottom(new ModifyMagicNumberAction(this.uuid, 1));
        AbstractDungeon.actionManager.addToBottom(new ModifyCostAction(this,+1));
        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(this.magicNumber));




    }


    @Override
    public AbstractCard makeCopy() {
        return new VortexShield();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.baseBlock = 12;

            this.initializeDescription();

        }
    }
}
