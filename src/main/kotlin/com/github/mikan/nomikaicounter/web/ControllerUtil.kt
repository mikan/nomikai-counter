/*
 * Copyright(C) 2014-2016 mikan All rights reserved.
 */
package com.github.mikan.nomikaicounter.web

import org.springframework.ui.Model
import java.util.*
import java.util.logging.Logger

/**
 * Utilities for controllers.
 *
 * @author mikan
 */
class ControllerUtil {
    companion object {
        var copyright = "mikan"
        var copyrightUrl = "https://github.com/mikan/nomikai-counter"
        var copyrightYear = "2014-2016"

        fun errorView(log: Logger, model: Model, message: String): String {
            model.addAttribute(ErrorController.ATTR_MESSAGE, message)
            log.warning(message)
            return ErrorController.VIEW
        }

        fun createDefaultAttributes(): Map<String, String> {
            val subModel = HashMap<String, String>()
            subModel.put("versionLabel", "Nomikai Counter v2.0.0")
            subModel.put("copyrightYear", copyrightYear)
            subModel.put("copyright", copyright)
            subModel.put("copyrightUrl", copyrightUrl)
            return Collections.unmodifiableMap(subModel)
        }

        fun isValidText(text: String): Boolean {
            return !(text.contains("<") || text.contains(">")
                    || text.contains("{") || text.contains("}")
                    || text.contains("[") || text.contains("]")
                    || text.contains("\"") || text.contains("\'"))
        }
    }
}