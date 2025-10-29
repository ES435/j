// ---- Config ----
const API_ITEMS = "http://localhost:8080/api/items";
const API_ANIME = "http://localhost:8080/api/anime";

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
async function searchAnime() {
    const q = $("q").value.trim();
    if (!q) return notify("Enter a query");
    const res = await fetch(`${API_ANIME}?q=${encodeURIComponent(q)}`);
    if (!res.ok) return notify("Search failed");
    const json = await res.json();             // Backend liefert Jikan-JSON
    const items = (json && json.data) ? json.data : [];

    const cards = items.slice(0, 12).map(a => {
        const img = a.images?.jpg?.image_url || a.images?.webp?.image_url || "";
        const title = a.title || a.title_english || a.title_japanese || "Untitled";
        return `
      <div class="card">
        <img src="${img}" alt="${title}">
        <div class="title">${title}</div>
      </div>
    `;
    }).join("");

    document.getElementById("cards").innerHTML = cards || "<p>No results.</p>";
}

// ---- Wire up ----
$("save").onclick = saveItem;
$("load").onclick = loadItems;
$("search").onclick = searchAnime;

// Optional: direkt beim Laden Items holen
// loadItems();