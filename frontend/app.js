// ---- Config ----
const API_ITEMS = "http://localhost:8080/api/items";
const API_ANIME = "http://localhost:8080/api/anime";
const API_ADD_ANIME = "http://localhost:8080/api/anime/add";

// ---- Helpers ----
const $ = (id) => document.getElementById(id);
const notify = (msg) => alert(msg);

// ---- Items ----
async function saveItem() {
    const text = $("text").value.trim();
    if (!text) return notify("Enter text");
    const res = await fetch(API_ITEMS, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ text })
    });
    if (!res.ok) return notify("Save failed");
    $("text").value = "";
    await loadItems();
}

async function loadItems() {
    const res = await fetch(API_ITEMS);
    if (!res.ok) return notify("Load failed");
    const data = await res.json();
    const list = $("list");
    list.innerHTML = "";
    data.forEach(i => {
        const li = document.createElement("li");
        const ts = i.createdAt ? new Date(i.createdAt).toLocaleString() : "";
        li.textContent = ts ? `${i.text} — ${ts}` : i.text;
        list.appendChild(li);
    });
}

// ---- Anime (Jikan via Backend) ----
function cardTemplate(a) {
    const img = a.images?.jpg?.image_url || a.images?.webp?.image_url || "";
    const title = a.title || a.title_english || a.title_japanese || "Untitled";
    const id = a.mal_id;
    return `
    <div class="card" data-id="${id}" data-title="${title}" data-img="${img}">
      <img src="${img}" alt="${title}">
      <div class="title">${title}</div>
      <div class="save">Klicken, um zu speichern</div>
    </div>
  `;
}

async function searchAnime() {
    const q = $("q").value.trim();
    if (!q) return notify("Enter a query");
    const res = await fetch(`${API_ANIME}?q=${encodeURIComponent(q)}`);
    if (!res.ok) return notify("Search failed");
    const json = await res.json();
    const items = (json && json.data) ? json.data : [];
    $("cards").innerHTML = items.slice(0, 24).map(cardTemplate).join("") || "<p>No results.</p>";
}

// ---- Click to add selected anime ----
document.addEventListener("click", async (e) => {
    const card = e.target.closest(".card");
    if (!card) return;
    const body = {
        malId: Number(card.dataset.id),
        title: card.dataset.title,
        imageUrl: card.dataset.img
    };
    const res = await fetch(API_ADD_ANIME, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(body)
    });
    if (res.ok) notify(`Gespeichert: ${body.title}`);
    else notify("Speichern fehlgeschlagen");
});

// ---- Load added animes ----
const API_ADDED = "http://localhost:8080/api/anime/add";

function addedCardTemplate(a) {
    const img = a.imageUrl || "";
    const title = a.title || "Untitled";
    return `
    <div class="card">
      <img src="${img}" alt="${title}">
      <div class="title">${title}</div>
    </div>
  `;
}

async function loadAddedAnimes() {
    const res = await fetch(API_ADDED);
    if (!res.ok) return notify("Load added animes failed");
    const data = await res.json();
    $("added").innerHTML =
        (data && data.length)
            ? data.map(addedCardTemplate).join("")
            : "<p>No added animes yet.</p>";
}

// ---- Wire up ----
$("save").onclick = saveItem;
$("load").onclick = loadItems;
$("search").onclick = searchAnime;
$("loadAdded").onclick = loadAddedAnimes;

// Optional: direkt beim Laden Items holen
// loadItems();