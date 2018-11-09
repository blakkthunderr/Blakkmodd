package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMiscAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import examplemod.patches.AbstractCardEnum;

public class BlakkBlade
        extends CustomCard {
    public static final String ID = "BlakkBlade";
    public static final String NAME = "Blakk Blade";
    public static final String DESCRIPTION = "Deal !D! damage to ALL enemies. Whenever you play this card, permanently increase its damage by !M!. Exhaust.";
    public static final String IMG_PATH = "img/BlakkBlade.png";
    private static final int COST = 1;



    public BlakkBlade() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.ATTACK, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);

        this.baseDamage = 1;
        this.misc = 2;
        this.damage = this.baseDamage;
        this.magicNumber = this.baseMagicNumber = 2;
        this.exhaust = true;
        this.isMultiDamage = true;
    }

    @Override
    public void applyPowers() {
        this.baseDamage = this.misc;
        super.applyPowers();
        this.initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {



        AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new CleaveEffect(), 0.1F));
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
        AbstractDungeon.actionManager.addToBottom(new IncreaseMiscAction(this.uuid, this.misc, this.magicNumber));


    }

    @Override
    public AbstractCard makeCopy() {
        return new BlakkBlade();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);


        }
    }
}
