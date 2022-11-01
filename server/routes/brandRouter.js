const Router = require("express");
const brandController = require("../controllers/BrandController");
const checkRole = require("../middleware/CheckRoleMiddleware");

const router = new Router();


router.post("/create", checkRole("ADMIN"), brandController.create)
router.get("/",brandController.getAll)

module.exports = router;