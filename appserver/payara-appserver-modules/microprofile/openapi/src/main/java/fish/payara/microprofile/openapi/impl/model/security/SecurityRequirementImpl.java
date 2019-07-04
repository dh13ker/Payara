/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) [2018] Payara Foundation and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://github.com/payara/Payara/blob/master/LICENSE.txt
 * See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at glassfish/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * The Payara Foundation designates this particular file as subject to the "Classpath"
 * exception as provided by the Payara Foundation in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */
package fish.payara.microprofile.openapi.impl.model.security;

import static fish.payara.microprofile.openapi.impl.model.util.ModelUtils.isAnnotationNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.microprofile.openapi.models.security.SecurityRequirement;

public class SecurityRequirementImpl extends LinkedHashMap<String, List<String>> implements SecurityRequirement {

    private static final long serialVersionUID = -677783376083861245L;

    public SecurityRequirementImpl() {
        super();
    }

    public SecurityRequirementImpl(Map<? extends String, ? extends List<String>> items) {
        super(items);
    }

    @Override
    public SecurityRequirement addScheme(String name, String item) {
        this.put(name, item == null ? new ArrayList<>() : Arrays.asList(item));
        return this;
    }

    @Override
    public SecurityRequirement addScheme(String name, List<String> item) {
        this.put(name, item == null ? new ArrayList<>() : item);
        return this;
    }

    @Override
    public SecurityRequirement addScheme(String name) {
        this.put(name, new ArrayList<>());
        return this;
    }

    @Override
    public void removeScheme(String securitySchemeName) {
        this.remove(securitySchemeName);
    }

    @Override
    public Map<String, List<String>> getSchemes() {
        return new SecurityRequirementImpl(this);
    }

    @Override
    public void setSchemes(Map<String, List<String>> items) {
        clear();
        putAll(items);
    }

    public static void merge(org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement from,
            SecurityRequirement to) {
        if (isAnnotationNull(from)) {
            return;
        }
        if (from.name() != null && !from.name().isEmpty()) {
            to.addScheme(from.name(), Arrays.asList(from.scopes()));
        }
    }

}
