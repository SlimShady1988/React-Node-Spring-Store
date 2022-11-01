const Router = require("express");
const typeController = require("../controllers/TypeController");
const checkRole = require("../middleware/CheckRoleMiddleware");

const router = new Router();


router.post("/create", checkRole("ADMIN"), typeController.create)
router.get("/",typeController.getAll)

module.exports = router;