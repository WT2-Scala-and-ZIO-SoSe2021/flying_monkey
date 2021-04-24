import org.scalatest.flatspec.AnyFlatSpec

class MainSpec extends AnyFlatSpec {
  "An empty Set" should "have size 0" in {
    assert(Set.empty.size == 0)
  }


}
