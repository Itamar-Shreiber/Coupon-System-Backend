package com.jb.couponSysItamar.service;

import com.jb.couponSysItamar.beans.*;
import com.jb.couponSysItamar.dto.CouponPayload;
import com.jb.couponSysItamar.dto.LoginReqDto;
import com.jb.couponSysItamar.dto.LoginResDto;
import com.jb.couponSysItamar.exceptions.CouponSystemException;
import com.jb.couponSysItamar.exceptions.ErrMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Qualifier
public class CompanyServiceImpl extends ClientService implements CompanyService {

    @Autowired
    private TokenService tokenService;
    @Override
    public LoginResDto loginDto(LoginReqDto req) throws CouponSystemException {
        if (companyRepository.existsByEmailAndPassword(req.getEmail(), req.getPassword())) {
            int companyId = companyRepository.findByEmailAndPassword(req.getEmail(), req.getPassword());
            User user = new User(companyId,req.getEmail(),req.getPassword(),req.getClientType());
            UUID token = tokenService.addUser(user);
            LoginResDto loginResDto = LoginResDto.builder().token(token).email(req.getEmail()).clientType(req.getClientType()).build();
            return loginResDto;
        }
            throw new CouponSystemException(ErrMsg.EXCEPTION_WRONG_EMIEl_OR_PASSWORD);
    }


    @Override
    public void addCoupon(UUID token, CouponPayload couponPayload) throws CouponSystemException {
        Coupon coupon = new Coupon(couponPayload);
        coupon.setCompany(companyRepository.findById(tokenService.getUserID(token)).orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_FOUND)));
        if (!companyRepository.existsById(coupon.getCompany().getId())) {
            throw new CouponSystemException(ErrMsg.ID_NOT_FOUND);
        }
        if (coupon.getCompany() != null) {
            if (couponRepository.existsByCompanyIdAndTitle(coupon.getCompany().getId(), coupon.getTitle())) {
                throw new CouponSystemException(ErrMsg.COMPANY_COUPON_TITLE_ALREADY_EXIST);
            }
        }
        couponRepository.save(coupon);
    }

    @Override
    public Coupon updateCoupon(UUID token, int couponId, CouponPayload couponPayload) throws CouponSystemException {
        Coupon coupon = new Coupon(couponPayload);
        coupon.setId(couponId);
        coupon.setCompany(companyRepository.findById(tokenService.getUserID(token)).orElseThrow(()->new CouponSystemException(ErrMsg.COMPANY_NOT_FOUND)));
        if (!couponRepository.existsById(couponId)) {
            throw new CouponSystemException(ErrMsg.ID_NOT_FOUND);
        }
        Coupon c0 = couponRepository.findById(couponId).orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_FOUND));
        if (c0.getId() != coupon.getId()) {
            throw new CouponSystemException(ErrMsg.CANNOT_UPDATE_COUPON_ID);
        }
        if (c0.getCompany().getId() != (coupon.getCompany().getId())) {
            throw new CouponSystemException(ErrMsg.CANNOT_UPDATE_COMPANY_ID);
        }

        return couponRepository.saveAndFlush(coupon);


    }


    @Override
    public void deleteCoupon(UUID token, int couponId) throws CouponSystemException {
        if (!companyRepository.existsById(tokenService.getUserID(token))) {
            throw new CouponSystemException(ErrMsg.COMPANY_NOT_FOUND);
        }
        if (!couponRepository.existsById(couponId)) {
            throw new CouponSystemException(ErrMsg.ID_NOT_FOUND);
        }
        couponRepository.deletePurchaseCoupon(couponId);
        couponRepository.deleteById(couponId);

    }


    @Override
    public Coupon getSingleCoupon(int couponId) throws CouponSystemException {
        return couponRepository.findById(couponId).orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_FOUND));
    }

    @Override
    public List<Coupon> getAllCompanyCouponsByToken(UUID uuid) throws CouponSystemException {
        int companyId= tokenService.getUserID(uuid);
        if (!companyRepository.existsById(companyId)) {
            throw new CouponSystemException(ErrMsg.ID_NOT_FOUND);
        }
        return couponRepository.findByCompanyId(companyId);
    }

    @Override
    public List<Coupon> getAllCompanyCouponsByCategory(int companyId, Category category) throws CouponSystemException {
        if (!companyRepository.existsById(companyId)) {
            throw new CouponSystemException(ErrMsg.ID_NOT_FOUND);
        }
        if (!couponRepository.existsByCategory(category)) {
            throw new CouponSystemException(ErrMsg.CATEGORY_DOSENT_EXIST);
        }
        return couponRepository.findByCompanyIdAndCategory(companyId, category);
    }


    @Override
    public List<Coupon> getAllCompanyCouponsByMaxPrice(int companyId, double maxPrice) throws CouponSystemException {
        if (!companyRepository.existsById(companyId)) {
            throw new CouponSystemException(ErrMsg.ID_NOT_FOUND);
        }
        return couponRepository.findByCompanyIdAndPriceLessThan(companyId, maxPrice);
    }

    @Override
    public Company getLoginCompany(int companyId) throws CouponSystemException {
        return companyRepository.findById(companyId).orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_FOUND));
    }
}
