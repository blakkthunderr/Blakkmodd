package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BlurPower;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import examplemod.patches.AbstractCardEnum;
import examplemod.powers.SoulPower;

public class TitanSword
        extends CustomCard {
    public static final String ID = "TitanSword";
    public static final String NAME = "Titan Sword";
    public static final String DESCRIPTION = "Deal !D! damage. Gain !M! Metallicize.";
    public static final String IMG_PATH = "img/TitanSword.png";
    private static final int COST = 3;
    private static final int ATTACK_DMG = 22;


    public TitanSword() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.ATTACK, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.RARE, CardTarget.ENEMY);

        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.damage=this.baseDamage = ATTACK_DMG;

        //this.setBackgroundTexture("img/custom_background_small.png", "img/custom_background_large.png");

        //this.setOrbTexture("img/custom_orb_small.png", "img/custom_orb_large.png");

        //this.setBannerTexture("img/custom_banner_large.png", "img/custom_banner_large.png");
    }




    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
                new DamageInfo(p, this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_HEAVY));

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new MetallicizePower(p, this.magicNumber), this.magicNumber));


    }

    @Override
    public AbstractCard makeCopy() {
        return new TitanSword();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(8);
            this.upgradeMagicNumber(1);

        }
    }
}
