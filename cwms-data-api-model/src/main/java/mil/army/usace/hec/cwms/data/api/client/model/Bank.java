/*
 * MIT License
 *
 * Copyright (c) 2024 Hydrologic Engineering Center
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package mil.army.usace.hec.cwms.data.api.client.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Bank {
    LEFT("Left", "L"),
    RIGHT("Right", "R");

    private final String name;
    private final String code;

    Bank(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName(){
        return name;
    }

    @JsonValue
    public String getCode(){
        return code;
    }

    public static Bank fromName(String name) {
        for(Bank bank : Bank.values()){
            if(bank.getName().equals(name)) {
                return bank;
            }
        }
        return null;
    }

    @JsonCreator
    public static Bank fromCode(String code) {
        if(code != null) {
            for(Bank bank : Bank.values()){
                if(bank.getCode().equals(code)) {
                    return bank;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return getName();
    }
}