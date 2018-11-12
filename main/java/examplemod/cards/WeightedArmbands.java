package examplemod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMiscAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import examplemod.patches.AbstractCardEnum;
import examplemod.powers.DisasterPower;

public class WeightedArmbands
        extends CustomCard {
    public static final String ID = "WeightedArmbands";
    public static final String NAME = "Weighted Armbands";
    public static final String DESCRIPTION = "When drawn, permanently increase this card's Strength gain by 1. When played, gain !M! Strength and reduce this card's Strength gain by 1. Exhaust. Ethereal.";
    public static final String IMG_PATH = "img/WeightedArmbands.png";
    private static final int COST = 2;



    public WeightedArmbands() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, CardColor.RED,
                CardRarity.UNCOMMON, CardTarget.SELF);


        this.misc = 0;
        this.magicNumber = this.baseMagicNumber = misc;
        this.exhaust = true;
        this.isEthereal = true;
    }

    @Override
    public void applyPowers() {
        this.baseMagicNumber = this.misc;
        super.applyPowers();
        this.initializeDescription();
    }

    @Override
    public void triggerWhenDrawn() {

        AbstractDungeon.actionManager.addToBottom(new IncreaseMiscAction(this.uuid, this.misc, 1));


    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {


        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, this.misc), this.misc));

        AbstractDungeon.actionManager.addToBottom(new IncreaseMiscAction(this.uuid, this.misc, -1));


    }

    @Override
    public AbstractCard makeCopy() {
        return new WeightedArmbands();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(1);


        }
    }
}
