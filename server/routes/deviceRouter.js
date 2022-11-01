const Router = require("express");
const deviceController = require("../controllers/DeviceController");
const checkRole = require("../middleware/CheckRoleMiddleware");
const router = new Router();


router.post("/create", checkRole("ADMIN"), deviceController.create)
router.get("/", deviceController.getAll)
router.get("/:id", deviceController.getOne)

module.exports = router;