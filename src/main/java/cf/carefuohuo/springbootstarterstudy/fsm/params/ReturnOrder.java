package cf.carefuohuo.springbootstarterstudy.fsm.params;

import cf.carefuohuo.springbootstarterstudy.fsm.ReturnFlowState;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 退货单
 * Created by jinwei on 19/4/2017.
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReturnOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 退单号
     */
    private String id;

    /**
     * 订单编号
     */
    private String tid;

    /**
     * 子单编号，crossSubTrade的Id
     */
    private String subTid;

    /**
     * 对应的ProviderTrade的Id
     */
    private String ptid;

    /**
     * linkedmall退货原因id
     */
    private Long thirdReasonId;

    /**
     * linkedmall原因内容
     */
    private String thirdReasonTips;

    /**
     * linkedmall侧 商家同意退单的留言，一般包含实际退货地址
     */
    private String thirdSellerAgreeMsg;


    /**
     * 尾款退单号
     */
    private String businessTailId;

    /**
     * 退货说明
     */
    private String description;

    /**
     * 退单附件
     */
    private List<String> images;

    /**
     * 退货单状态
     */
    private ReturnFlowState returnFlowState;

    /**
     * 邀请人分销员id
     */
    private String distributorId;

    /**
     * 邀请人会员id
     */
    private String inviteeId;

    /**
     * 小店名称
     */
    private String shopName;

    /**
     * 分销员名称
     */
    private String distributorName;

    /**
     * 拒绝原因
     */
    private String rejectReason;

    /**
     * 数联支付通道里 -> 支付方式编号
     */
    private String dcPayWayId;

    /**
     * 线下退款账户
     */
    private Long offlineAccountId;


    private LocalDateTime createTime;

    /**
     * 退单完成时间
     */
    private LocalDateTime finishTime;

    /**
     * 是否被结算
     */
    private Boolean hasBeanSettled;

    /**
     * 退款失败原因
     */
    private String refundFailedReason;

    /**
     * 退货赠品
     */
    private Boolean returnGift;

    /**
     * 退货加价购
     */
    private List<String> returnPreferentialIds;

    /**
     * 审核时间
     */
    private LocalDateTime auditTime;

    /**
     * 订单所属第三方平台的订单id
     */
    private String thirdPlatformOrderId;

    /**
     * 第三方内部卖家名称
     */
    private String thirdSellerName;

    /**
     * 第三方内部卖家编号
     */
    private String thirdSellerId;

    /**
     * 第三方平台支付失败状态  true:失败 false:成功
     */
    private Boolean thirdPlatformPayErrorFlag;

    /**
     * 外部商家订单号
     * linkedMall -> 淘宝订单号
     */
    private String outOrderId;

    /**
     * 退单的卖家备注
     */
    private String sellerRemark;

    private String sellPlatformReturnId;

    /**
     * 场景值:全部、直播间（下单场景值1176、1177）、橱窗（场景值1195）、视频号活动（场景值1191）、视频号商店（场景值1175）
     */
    private Integer sceneGroup;

    private Long marketingId;
}
