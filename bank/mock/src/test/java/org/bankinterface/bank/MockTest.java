/*
 * Copyright 2013 bankinterface.org
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
 */
package org.bankinterface.bank;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.bankinterface.json.JsonHelper;
import org.bankinterface.param.AsyncPayParam;
import org.bankinterface.result.PrePayResult;
import org.junit.Before;
import org.junit.Test;

public class MockTest {
    private Mock bank = new Mock();

    @Before
    public void setUp() throws Exception {
        bank.init();
    }

    @Test
    public final void testPrePayString() {
        AsyncPayParam param = new AsyncPayParam();
        Map<String, String> config = new HashMap<String, String>();
        config.put("merchantNo", "merchantNo");
        config.put("requestURL", "https://api.xxxbank.com/xxx");
        Date current = new Date();
        param.setOrderNO(current.getTime() + "");
        param.setOrderDate(current);
        param.setOrderAmount(BigDecimal.TEN);
        param.setConfig(config);
        String json = bank.prePay(JsonHelper.toJson(param));
        System.out.println(json);
        PrePayResult result = JsonHelper.fromJson(json, PrePayResult.class);
        assertTrue(result.isSuccess());
    }

}
