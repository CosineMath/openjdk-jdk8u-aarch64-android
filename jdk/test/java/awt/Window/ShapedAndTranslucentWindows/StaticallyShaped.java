/*
 * Copyright (c) 2010, 2014, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

import java.awt.*;


/*
 * @test
 * @summary Check if statically shaped window is dragged and resized
 *          correctly.
 * @author Dmitriy Ermashov (dmitriy.ermashov@oracle.com)
 * @library ../../../../lib/testlibrary
 * @build Common ExtendedRobot
 * @run main StaticallyShaped
 */

public class StaticallyShaped extends Common {

    public static void main(String[] args) throws Exception {
        if (checkTranslucencyMode(GraphicsDevice.WindowTranslucency.PERPIXEL_TRANSPARENT))
            for (Class<Window> windowClass: WINDOWS_TO_TEST){
                new StaticallyShaped(windowClass).doTest();
            }
    }

    public StaticallyShaped(Class windowClass) throws Exception{ super(windowClass); }
    public void applyShape(){ applyStaticShape(); }

    public void doTest() throws Exception{
        super.doTest();

        checkStaticShape();

        // Drag
        Point location = window.getLocationOnScreen();
        robot.dragAndDrop(location.x + dl, location.y + 5, location.x + dl + random.nextInt(dl), location.y + random.nextInt(dl));
        robot.waitForIdle(delay);
        checkStaticShape();

        // Resize
        location = window.getLocationOnScreen();
        robot.dragAndDrop(location.x + 4, location.y + 4, location.x + random.nextInt(2*dl)-dl, location.y + random.nextInt(2*dl)-dl);
        robot.waitForIdle(delay);
        checkStaticShape();

        dispose();
    }
}
