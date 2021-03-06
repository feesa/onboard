/*******************************************************************************
 * Copyright [2015] [Onboard team of SERC, Peking University]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.onboard.service.security.interceptors.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.onboard.service.security.exception.NoPermissionException;
import com.onboard.service.security.interceptors.ManagerRequired;
import com.onboard.service.web.SessionService;

/**
 * 判断是否是管理员的 Interceptor
 * 
 * @author txq
 * 
 */

@Service("managerRequiredBean")
public class ManagerRequiredImpl extends HandlerInterceptorAdapter implements ManagerRequired {

    @Autowired
    protected SessionService session;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!session.getCurrentUser().getIsManager()) {
            throw new NoPermissionException("403");
        }
        return true;
    }
}
